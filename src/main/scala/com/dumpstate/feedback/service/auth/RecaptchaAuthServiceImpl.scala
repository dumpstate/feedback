package com.dumpstate.feedback.service.auth

import scala.concurrent.Future.successful
import scala.concurrent.Future

import org.slf4j.Logger

class RecaptchaAuthServiceImpl(logger: Logger) extends AuthService {
  override def authenticate(token: String): Future[Boolean] = {
    //TODO implement!

    logger.info(s"authenticate: $token")
    successful(true)
  }
}
