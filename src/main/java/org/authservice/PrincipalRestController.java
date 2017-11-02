
package org.authservice;


import java.util.HashMap;
import java.util.Map;

import java.security.Principal;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.core.authority.AuthorityUtils;


@RestController
public class PrincipalRestController
{

	public Principal getPrincipal( Principal principal ) {
		return principal;
	}

	@GetMapping( "/user" )
	public Map<String, Object> getUser( OAuth2Authentication user )
	{
		Map<String, Object> userInfo = new HashMap<>();

		userInfo.put( "user", user.getUserAuthentication().getPrincipal() );
		userInfo.put( "authorities", AuthorityUtils.authorityListToSet( user.getUserAuthentication().getAuthorities() ) );

		return userInfo;
	}

}
