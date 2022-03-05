package com.apiProdutosBlack.apiProdutosBlack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =  true)
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private final CustomUserDetailService userDetailService;
	
	
	public SecurityConfig(CustomUserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { // o httpbasic é para o header simples do postman, pode ser uma pagina html
		
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); // Exige autenticação para todas requisições
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
		
		/*PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		 auth.inMemoryAuthentication()
		  .withUser("vitor").password(encoder.encode("321123")).roles("USER").credentialsExpired(true)
		  .and()
		  .withUser("admin").password(encoder.encode("91851540")).roles("USER", "ADMIN");*/
	}
}
