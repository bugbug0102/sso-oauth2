package org.b0102.sso.oauth2.res2.server

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.OAuth2Authentication
import collection.JavaConverters._
import org.slf4j.LoggerFactory

@RestController
private[server] class MainController 
{
  private val logger = LoggerFactory.getLogger(classOf[MainController])
  @RequestMapping(Array("/"))
  private[server] def main(auth:Authentication):String = {
   val o = auth.asInstanceOf[OAuth2Authentication]
   
   o.getAuthorities.asScala.foreach{ g => logger.debug("{}", g.getAuthority) }
   return s"Hello World !!! (res2)" 
    
  }
}