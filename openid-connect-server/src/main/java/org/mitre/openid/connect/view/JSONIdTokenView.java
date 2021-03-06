/*******************************************************************************
 * Copyright 2012 The MITRE Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.mitre.openid.connect.view;

import java.io.Writer;
import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mitre.jwt.model.ClaimSet;
import org.mitre.openid.connect.model.IdToken;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JSONIdTokenView extends AbstractView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Gson gson = new GsonBuilder()
			.setExclusionStrategies(new ExclusionStrategy() {
				
				public boolean shouldSkipField(FieldAttributes f) {
					
					return false;
				}
				
				public boolean shouldSkipClass(Class<?> clazz) {
					// skip the JPA binding wrapper
					if (clazz.equals(BeanPropertyBindingResult.class)) {
						return true;
					}
					return false;
				}
								
			})
			.registerTypeHierarchyAdapter(ClaimSet.class, new JsonSerializer<ClaimSet>() {
				@Override
				public JsonElement serialize(ClaimSet src, Type typeOfSrc, JsonSerializationContext context) {
					if (src != null) {
						return src.getAsJsonObject();
					} else {
						return JsonNull.INSTANCE;
					}
				}
			})
			.create();

		response.setContentType("application/json");
		
		Writer out = response.getWriter();
		
		Object obj = model.get("entity");
		if (obj == null) {
			obj = model;
		}
		
		gson.toJson(obj, out);
	}
	
}
