package org.b0102.sso.oauth2.res3.server

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
import javax.servlet.http.HttpServletRequest

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter extends Filter 
{
  private val logger = LoggerFactory.getLogger(classOf[CorsFilter])
  
  override def doFilter(request:ServletRequest, response:ServletResponse , chain:FilterChain):Unit =
  {
    val httpResponse = response.asInstanceOf[HttpServletResponse]
    val httpRequest = request.asInstanceOf[HttpServletRequest]
    httpResponse.setHeader("Access-Control-Allow-Origin", "*")
    httpResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
    httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, X-Requested-With, Session")
    httpResponse.setHeader("Access-Control-Max-Age", "3600")
    
    if("OPTIONS".equalsIgnoreCase(httpRequest.getMethod))
    {
      httpResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }else
    {
      chain.doFilter(request, response)  
    }
    
  }
  
  override def init(filterConfig:FilterConfig):Unit = {}
  override def destroy():Unit = {}
  
}