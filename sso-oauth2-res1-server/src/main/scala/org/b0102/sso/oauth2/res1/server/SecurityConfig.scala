package org.b0102.sso.oauth2.res1.server

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import collection.JavaConverters._

@Configuration
@EnableOAuth2Sso
private[server] class SecurityConfig extends WebSecurityConfigurerAdapter 
{
  override protected def configure(http:HttpSecurity):Unit =
  {
    http
      .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
      .logoutSuccessUrl("http://127.0.0.1:30001/as/logout")
    .and()
      .antMatcher("/**")
      .authorizeRequests()
      .anyRequest()
      .authenticated()
    .and().cors()
    
  }
  
  private[server] def corsConfiguration():CorsConfiguration  =
  {
    val cc = new CorsConfiguration()
    cc.setAllowedOrigins(List("http://127.0.0.1:20001").asJava)
    cc.setAllowedMethods(List("*").asJava)
    return cc
  }
  
}