package org.b0102.sso.oauth2.auth.server

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.context.annotation.Bean
import javax.sql.DataSource
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.context.annotation.Primary
import org.springframework.security.oauth2.provider.token.DefaultTokenServices

@Configuration
@EnableAutoConfiguration(exclude=Array(
		classOf[GroovyTemplateAutoConfiguration], 
		classOf[GsonAutoConfiguration], 
		classOf[WebSocketAutoConfiguration],
		classOf[JmxAutoConfiguration],
		classOf[ActiveMQAutoConfiguration],
		classOf[JacksonAutoConfiguration]
		))
@ComponentScan
private[server] class Config
{  
  
  @Bean
  private[server] def accessTokenConverter():AccessTokenConverter =
  {
    val jatc = new JwtAccessTokenConverter()
    jatc.setSigningKey("123")
    jatc.setVerifierKey("123")
    return jatc
  }
  
  @Bean
  private[server] def tokenStore(converter:AccessTokenConverter):TokenStore = new JwtTokenStore(converter.asInstanceOf[JwtAccessTokenConverter])
  
  @Bean
  @Primary
  private[server] def tokenServices(tokenStore:TokenStore):DefaultTokenServices  =
  {
    val dts = new DefaultTokenServices()
    dts.setTokenStore(tokenStore)
    dts.setSupportRefreshToken(true)
    return dts
  }
  
}

object AuthServer 
{
  private val logger = LoggerFactory.getLogger(this.getClass)
  def main(args:Array[String]):Unit =
  {
    val ctx = new SpringApplicationBuilder(classOf[Config]).web(true).run(args:_*)
  }
}