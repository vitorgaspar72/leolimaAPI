package com.apiLeoLima.apiLeolima.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService{
	private final UserRepository repository;
	
	
	public CustomUserDetailService(UserRepository repository) {
		this.repository = repository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =Optional.ofNullable(repository.findByUsername(username)).orElseThrow(()-> new  UsernameNotFoundException("User not found"));
		
	    List<GrantedAuthority> authorityListAdmin=AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
	    List<GrantedAuthority> authorityListUser=AuthorityUtils.createAuthorityList("ROLE_USER");
	    
	    return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword()
	    		,user.isAdmin() ? authorityListAdmin : authorityListUser);
	}

}
