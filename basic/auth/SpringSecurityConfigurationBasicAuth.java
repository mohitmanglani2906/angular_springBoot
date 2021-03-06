package com.mohit2906.basic.auth;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		System.out.println("_____ HttpSecurity _____");
		try	
		{
		
			http
			.csrf().disable()
			.headers()
			.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin","*"))
			.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods","GET, POST, OPTIONS, PUT, PATCH, DELETE"))
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
			.anyRequest().authenticated()
			.and()
			//.formLogin().and()
			.httpBasic();
			
			System.out.println("_____ HttpSecurity _____");

		}
		
		catch(Exception e) {
			System.out.println("____ Authorization Error ____ " + e.getStackTrace());
		}
	}
	
//	@Bean
//	@Primary
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
