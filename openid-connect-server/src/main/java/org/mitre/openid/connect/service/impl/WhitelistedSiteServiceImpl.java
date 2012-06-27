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
package org.mitre.openid.connect.service.impl;

import java.util.Collection;

import org.mitre.openid.connect.model.UserInfo;
import org.mitre.openid.connect.model.WhitelistedSite;
import org.mitre.openid.connect.repository.WhitelistedSiteRepository;
import org.mitre.openid.connect.service.WhitelistedSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the WhitelistedSiteService
 * 
 * @author Michael Joseph Walsh
 * 
 */
@Service
@Transactional
public class WhitelistedSiteServiceImpl implements WhitelistedSiteService {

	@Autowired
	private WhitelistedSiteRepository whitelistedSiteRepository;

	/**
	 * Default constructor
	 */
	public WhitelistedSiteServiceImpl() {

	}

	/**
	 * Constructor for use in test harnesses.
	 * 
	 * @param repository
	 */
	public WhitelistedSiteServiceImpl(
			WhitelistedSiteRepository whitelistedSiteRepository) {
		this.whitelistedSiteRepository = whitelistedSiteRepository;
	}	
	
	@Override
	public WhitelistedSite getById(Long id) {
		return whitelistedSiteRepository.getById(id);
	}

	@Override
	public void remove(WhitelistedSite whitelistedSite) {
		whitelistedSiteRepository.remove(whitelistedSite);
	}

	@Override
	public void removeById(Long id) {
	}

	@Override
	public WhitelistedSite save(WhitelistedSite whitelistedSite) {
		return whitelistedSiteRepository.save(whitelistedSite);
	}

	@Override
	public Collection<WhitelistedSite> getAll() {
		return whitelistedSiteRepository.getAll();
	}

	@Override
	public WhitelistedSite getByClientDetails(ClientDetails client) {
		return whitelistedSiteRepository.getByClientDetails(client);
	}

	@Override
	public Collection<WhitelistedSite> getByCreator(UserInfo creator) {
		return whitelistedSiteRepository.getByCreator(creator);
	}

}
