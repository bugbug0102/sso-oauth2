package org.b0102.sso.oauth2.auth.server

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

@Configuration
@EnableWebSecurity
@EnableResourceServer
private[server] class SecurityConfig extends WebSecurityConfigurerAdapter 
{
  override def configure(web:WebSecurity):Unit =
  {
  }
  
  override protected def configure(http:HttpSecurity):Unit =
  {
    http
      .sessionManagement().invalidSessionUrl("/")
    .and
      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
    .and
      .formLogin()
  }
  
  @Autowired
  def configureGlobal(auth:AuthenticationManagerBuilder):Unit =
  {
    auth.inMemoryAuthentication().withUser("bugbug0102").password("0102").roles("USER","ADMIN","BUGBUG")
  }
  
  
  
}