package org.b0102.sso.oauth2.profile.server

import javax.servlet.Filter
import org.springframework.stereotype.Component
import org.springframework.core.annotation.Order
import org.springframework.core.Ordered
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse
import javax.servlet.FilterConfig
import org.slf4j.LoggerFactory

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter extends Filter 
{
  private val logger = LoggerFactory.getLogger(classOf[CorsFilter])
  
  override def doFilter(request:ServletRequest, response:ServletResponse , chain:FilterChain):Unit =
  {
    val httpResponse = response.asInstanceOf[HttpServletResponse]
    httpResponse.setHeader("Access-Control-Allow-Origin", "*")
    httpResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
    httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with")
    httpResponse.setHeader("Access-Control-Max-Age", "3600")
    chain.doFilter(request, response)
  }
  
  override def init(filterConfig:FilterConfig):Unit = {}
  override def destroy():Unit = {}
  
}