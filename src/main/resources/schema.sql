
USE CONTACTS;


CREATE TABLE IF NOT EXISTS USER_ACCOUNT
(
 	id			bigint(20) NOT NULL AUTO_INCREMENT,
  	username	varchar(20) NOT NULL,
 	password	varchar(20) NOT NULL,
 	role		varchar(20) ,
 	active		boolean ,
 	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CLIENT
(
	id						BIGINT(20) NOT NULL AUTO_INCREMENT ,
	clientId				VARCHAR(40) ,
	secret					VARCHAR(40) ,
	scopes					VARCHAR(40) ,
	authorizedGrantTypes	VARCHAR(100) ,
	authorities				VARCHAR(40) ,
	autoApproveScopes		VARCHAR(40) ,
	PRIMARY KEY( id )
);