package org.b0102.sso.oauth2.res3.server

import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
private[server] class Res3Config extends ResourceServerConfigurerAdapter
{
  override def configure(resources:ResourceServerSecurityConfigurer):Unit =
  {
    resources.resourceId("profile")
  }
}