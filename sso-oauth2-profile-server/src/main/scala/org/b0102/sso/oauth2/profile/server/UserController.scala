package org.b0102.sso.oauth2.profile.server

import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import org.springframework.web.bind.annotation.RequestMapping
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.Authentication

@RestController
private[server] class UserController 
{
  private val logger = LoggerFactory.getLogger(classOf[UserController])
  
  @RequestMapping(value = Array("/user"))
  def user(auth:Authentication, user:Principal):Principal = 
  {
    logger.debug("user {}", user)
    logger.debug("auth {}", auth)
    return user 
  }
  
}