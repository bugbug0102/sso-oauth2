package org.b0102.sso.oauth2.profile.server

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso

@Configuration
@EnableResourceServer
private[server] class ProfileConfig extends ResourceServerConfigurerAdapter
{
  @Autowired
  private var tokenStore:TokenStore = _
  
  override def configure(resources:ResourceServerSecurityConfigurer):Unit =
  {
    resources.tokenStore(tokenStore)
    resources.resourceId("profile")
  }
}