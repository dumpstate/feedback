package com.dumpstate.feedback.service.auth

import scala.concurrent.Future

trait AuthService {
  def authenticate(token: String): Future[Boolean]
}
