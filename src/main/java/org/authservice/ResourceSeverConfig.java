package org.authservice;


import org.springframework.context.annotation.Configuration;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
@EnableResourceServer
public class ResourceSeverConfig extends ResourceServerConfigurerAdapter
{

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
			/*.requestMatchers()
				.antMatchers( "/login", "/oauth/authorize", "/user" )
				.and()*/
			.authorizeRequests()
				.antMatchers( "/" ).authenticated()
				.antMatchers( "/user" ).permitAll()
				//.antMatchers( "/login**" ).permitAll()
				.and()
			.formLogin();
	}

}
