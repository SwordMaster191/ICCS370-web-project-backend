package io.muzoo.ssc.project.backend.config;

import io.muzoo.ssc.project.backend.auth.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private OurUserDetailsService ourUserDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Configuring security for REST backend APIs because we will not login using form login anymore (EP5)
		http.csrf().disable(); //Disable CSRF (Normally it should not be disable but idk it's gonna be easier without it.

		//Permit root and /api/login and /api/logout
		http.authorizeRequests()
				.antMatchers("/", "/api/login", "/api/logout").permitAll();

		//permit all OPTIONS requests
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

		//Handle error output as JSON for unauthorized access
		http.exceptionHandling().authenticationEntryPoint(new JsonHttp403ForbiddenEntryPoint());

		//Set every other path to require authentication
		http.authorizeRequests().antMatchers("/**").authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}


	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return ourUserDetailsService;
	}

	class JsonHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint{

		@Override
		public void commence(HttpServletRequest httpServletRequest,
							 HttpServletResponse httpServletResponse,
							 AuthenticationException e) throws IOException, ServletException {
			//Output JSON msg
			//Println for now
			httpServletResponse.getWriter().println("You are not allowed to access this page.");

		}
	}
}
