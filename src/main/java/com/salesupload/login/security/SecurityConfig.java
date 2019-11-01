package com.salesupload.login.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	Environment environment;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	AuthenticationSuccessHandler loginAuthenticationSuccessHandler() {
		return new LoginAuthenticationSuccessHandler();
	}
	
	@Bean
	LogoutSuccessHandler logoutSuccessHandler() {
		return new MyLogoutSuccessHandler();
	}
	
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new MyAccessDeniedHandler();
	}
	
	@Bean
	DaoAuthenticationProvider provider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
            web.ignoring()
            // Spring Security should completely ignore URLs starting with /assets/ and thymejs/
                            .antMatchers("/assets/**", "/thymejs/**");
//            web.ignoring().antMatchers("/css/**","/font-awesome/**","/assets/**", "/thymejs/**","/images/**","/js/**","/vendors/**","/jspJS/**","/download/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { 
    	//.defaultSuccessUrl("/timesheet")//Set After Success URL\
    	//.antMatchers("/*").permitAll()
    	// clearAuthentication(true) 
    	// .invalidateHttpSession(true)
    	//.antMatchers("/common**").permitAll()
    	http.authorizeRequests()
    	.antMatchers("/login*").permitAll()
//    	.antMatchers("/usermaster/**").hasRole("GUEST")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login-process")
		.usernameParameter("login")
		.passwordParameter("password")			
		.successHandler(loginAuthenticationSuccessHandler())
		.and()
		.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessHandler(logoutSuccessHandler())
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
		.and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
    	try {
			auth.authenticationProvider(provider());
		} catch (Exception e) { 
			e.printStackTrace();
		} 
    } 
} 