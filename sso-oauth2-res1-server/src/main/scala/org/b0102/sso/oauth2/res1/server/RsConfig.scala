package org.b0102.sso.oauth2.res1.server

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.security.oauth2.provider.token.DefaultTokenServices

@Configuration
private[server] class RsConfig extends ResourceServerConfigurerAdapter
{
  override def configure(resources:ResourceServerSecurityConfigurer):Unit =
  {
    resources.resourceId("res1")
  }
  
  
}