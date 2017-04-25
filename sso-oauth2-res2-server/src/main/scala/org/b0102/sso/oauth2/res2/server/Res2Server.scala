package org.b0102.sso.oauth2.res2.server

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.TokenStore
import javax.sql.DataSource
import org.springframework.context.annotation.Bean

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
  
} 

object Res2Server 
{
  private val logger = LoggerFactory.getLogger(this.getClass)
  def main(args:Array[String]):Unit =
  {
    val ctx = new SpringApplicationBuilder(classOf[Config]).web(true).run(args:_*)
  }
}