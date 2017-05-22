package org.b0102.sso.oauth2.res3.server

import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import org.springframework.web.bind.annotation.RequestMapping
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.Authentication
import org.springframework.security.access.annotation.Secured

@RestController
private[server] class Res3Controller 
{
  private val logger = LoggerFactory.getLogger(classOf[Res3Controller])
  
  @RequestMapping(value = Array("/user"))
  def user(auth:Authentication, user:Principal):Principal = 
  {
    logger.debug("user {}", user)
    logger.debug("auth {}", auth)
    return user 
  }
  
//  @Secured(Array("ROLE_OOOOOOOOOOOO"))
  @RequestMapping(value = Array("/hello"))
  def hello(auth:Authentication, user:Principal):String = 
  {
    logger.debug("hello me")
    logger.debug("user {}", user)
    logger.debug("auth {}", auth)
    return "world" 
  }
 
  
}