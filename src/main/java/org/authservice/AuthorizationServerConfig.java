
package org.authservice;


import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{

	private AuthenticationManager authenticationManager;


	@Autowired
	public AuthorizationServerConfig( AuthenticationManager authenticationManager ) {
		this.authenticationManager	= authenticationManager;
	}


	@Override
	public void configure( AuthorizationServerSecurityConfigurer security ) throws Exception {
		security.checkTokenAccess( "isAuthenticated()" );
	}

	@Override
	public void configure( ClientDetailsServiceConfigurer clients ) throws Exception
	{
		clients.inMemory()
			.withClient( "gui-vaadin" )
			.secret( "123" )
			.authorizedGrantTypes( "refresh_token", "password", "client_credentials" )
			.authorities( "ROLE_CLIENT", "ROLE_TRUSTED_CLIENT" )
			.scopes( "webclient", "mobileclient" )
			.resourceIds( "oauth2-resource" )
			.accessTokenValiditySeconds( 5000 );
	}
	
	@Override
	public void configure( AuthorizationServerEndpointsConfigurer endpoints ) throws Exception {
		endpoints.authenticationManager( authenticationManager );
	}

}
