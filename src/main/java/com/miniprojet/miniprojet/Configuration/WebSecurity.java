package com.miniprojet.miniprojet.Configuration;

import com.miniprojet.miniprojet.Repository.CompteRepository;
import com.miniprojet.miniprojet.Service.CompteDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
@Configuration 
public class WebSecurity extends WebSecurityConfigurerAdapter { 
   @Autowired CompteRepository compteRepository;
   @Bean
   public UserDetailsService userDetailsService() {
      return new CompteDetailsServiceImpl(compteRepository);
   }

   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }
   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
   }

   @Bean 
   public PasswordEncoder passwordEncoder() { 
      return new BCryptPasswordEncoder(); 
   } 
   @Override 
   protected void configure(HttpSecurity http) throws Exception { 
      http 
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/Profil/**").hasAuthority("Client")
      .antMatchers("/Admin/**").hasAuthority("Admin")
      .anyRequest().permitAll() //any other request
      .and() 
      .formLogin().loginPage("/login").permitAll() 
      .and() 
      .logout().invalidateHttpSession(true).clearAuthentication(true).permitAll().logoutSuccessUrl("/"); 

      http.headers().frameOptions().disable();
   }
}