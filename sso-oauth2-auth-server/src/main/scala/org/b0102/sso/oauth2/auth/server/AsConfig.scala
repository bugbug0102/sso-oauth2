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

@Configuration
@EnableAuthorizationServer
private[server] class AsConfig extends AuthorizationServerConfigurerAdapter 
{
  @Autowired
  private var tokenStore:TokenStore = _
  
  @Autowired 
  private var authenticationManager:AuthenticationManager = _
  override def configure(endpoints:AuthorizationServerEndpointsConfigurer):Unit =
  {
    endpoints
      .authenticationManager(authenticationManager)
      .tokenStore(tokenStore)
    
  }
  
  override def configure(oauthServer:AuthorizationServerSecurityConfigurer):Unit =
  {
    oauthServer.allowFormAuthenticationForClients()
  }
  
  override def configure(clients:ClientDetailsServiceConfigurer):Unit = 
  {
			clients.inMemory()
			  .withClient("my-trusted-client")
					.authorizedGrantTypes("password", "authorization_code",	"refresh_token", "implicit")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read", "write", "trust").resourceIds("sparklr")
					.accessTokenValiditySeconds(60).and()
					
				.withClient("my-client-with-registered-redirect")
					.authorizedGrantTypes("authorization_code")
					.scopes("read","trust").resourceIds("profile","res1","res2")
					.redirectUris("http://127.0.0.1:40001/res1/login","http://127.0.0.1:40003/res2/login")
					.secret("secret001").autoApprove(true)
					.and()
					
				.withClient("my-client-with-secret")
					.authorizedGrantTypes("client_credentials", "password")
					.authorities("ROLE_CLIENT").scopes("read").resourceIds("sparklr")
					.secret("secret")    
  }
}