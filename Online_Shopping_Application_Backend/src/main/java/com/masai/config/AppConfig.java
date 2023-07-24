package com.masai.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
	
	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception{
		
		http.sessionManagement(sessionManagement -> 
		sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		.cors(cors -> {
			
			  cors.configurationSource(new CorsConfigurationSource() {
				
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						
						CorsConfiguration cfg= new CorsConfiguration();
						
						cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
						cfg.setAllowedMethods(Collections.singletonList("*"));
						cfg.setAllowCredentials(true);
						cfg.setAllowedHeaders(Collections.singletonList("*"));
						cfg.setExposedHeaders(Arrays.asList("Authorization"));
						return cfg;
					}
			  });
		})
		.authorizeHttpRequests(
			  auth ->{	
				
		      auth.requestMatchers(HttpMethod.POST, "/customers").permitAll()
		      
		      .requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
		      
		      .requestMatchers(HttpMethod.GET, "/customers").hasRole("ADMIN")
		      
		      .requestMatchers(HttpMethod.GET, "/carts/view/**").hasRole("USER")
		      
		      .requestMatchers(HttpMethod.GET, "/customers/**","/orders/**","/orders/date")
		      								.hasAnyRole("ADMIN","USER")
		      								
		      .requestMatchers(HttpMethod.GET,"/products","/products/category/**",
		    		  "/products/search","/products/sorted","/products/filter", "/products/category", "/products/brand").permitAll()								
		      											
		      .requestMatchers(HttpMethod.POST, "/admins","/products").hasRole("ADMIN")
		      
		      .requestMatchers(HttpMethod.POST,"/carts/**","/orders/**").hasRole("USER")
		      
		      .requestMatchers(HttpMethod.PUT, "/customers/**","/carts/**").hasRole("USER")
		      
		      .requestMatchers(HttpMethod.PUT, "/customers/**","/products").hasRole("ADMIN")
		      
		      .requestMatchers(HttpMethod.DELETE, "/customers/**","/carts/**","/carts/removeAll/**","/orders/cancel")
		      								.hasAnyRole("ADMIN","USER")
		      
		      .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
		      
		      .anyRequest().authenticated();
				  
								
			
		})
		.csrf(csrf -> csrf.disable())
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
}
