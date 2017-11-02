
package org.authservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class UserAccountService implements UserDetailsService
{

	public static final Logger logger = LoggerFactory.getLogger( UserAccountService.class );

	private UserAccountRepository userAccountRepository;


	@Autowired
	public UserAccountService( UserAccountRepository userAccountRepository ) {
		this.userAccountRepository = userAccountRepository;
	}
	

	public UserDetails loadUserByUsername( String username )
	{
		return userAccountRepository.findByUsername( username )
					.map( account -> new User( account.getUsername(), account.getPassword(),
							account.isActive(), true, true, true, AuthorityUtils.createAuthorityList( account.getRole() ) ) )
					.orElseThrow( () -> new UsernameNotFoundException( "Couldn't find the username " + username ) );
	}


}
