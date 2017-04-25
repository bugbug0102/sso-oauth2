package org.b0102.sso.oauth2.res2.server

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.core.annotation.Order
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso

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
  }
  
}