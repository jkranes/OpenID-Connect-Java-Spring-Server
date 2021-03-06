CREATE TABLE clientdetails (
	clientId VARCHAR(256),
	clientSecret VARCHAR(2000),
	clientName VARCHAR(256),
	clientDescription VARCHAR(2000),
	allowRefresh TINYINT,
	accessTokenValiditySeconds BIGINT,
	refreshTokenValiditySeconds BIGINT,
	owner VARCHAR(256)
);