package org.b0102.sso.oauth2.auth.server

import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.context.annotation.Bean
import javax.sql.DataSource
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import collection.JavaConverters._
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter


private[server] class CustomTokenEnhancer extends TokenEnhancer
{
  override def enhance(accessToken:OAuth2AccessToken, authentication:OAuth2Authentication):OAuth2AccessToken =
  {
    return accessToken 
  }
}

@Configuration
@EnableAuthorizationServer
private[server] class AsConfig extends AuthorizationServerConfigurerAdapter 
{
  @Autowired
  private var tokenStore:TokenStore = _
  
  @Autowired
  private var accessTokenConverter:AccessTokenConverter = _
  
  @Autowired
  private var dataSource:DataSource = _
  
  @Autowired 
  private var authenticationManager:AuthenticationManager = _
  
  override def configure(endpoints:AuthorizationServerEndpointsConfigurer):Unit =
  {
//    val chain = new TokenEnhancerChain()
//    val te = new CustomTokenEnhancer()
    
//    chain.setTokenEnhancers(List[TokenEnhancer](te, accessTokenConverter.asInstanceOf[JwtAccessTokenConverter]).asJava)
    
    endpoints
      .tokenStore(tokenStore)
      .accessTokenConverter(accessTokenConverter)
//      .tokenEnhancer(chain)
      .authenticationManager(authenticationManager)
  }
  
  override def configure(oauthServer:AuthorizationServerSecurityConfigurer):Unit =
  {
    oauthServer.allowFormAuthenticationForClients()
  }
  
  override def configure(clients:ClientDetailsServiceConfigurer):Unit = 
  {
			clients.jdbc(dataSource)
  }
}