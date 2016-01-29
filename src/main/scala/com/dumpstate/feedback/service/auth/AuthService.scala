package com.dumpstate.feedback.service.auth

import scala.concurrent.Future

import com.dumpstate.feedback.dto.Secret

trait AuthService {
  def authenticate(token: String, secret: Secret): Future[Boolean]
}
