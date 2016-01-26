package com.dumpstate.feedback.service.email

import scala.concurrent.Future

import spray.json.JsObject

import com.dumpstate.feedback.config.dto.AppEntry.PostmarkConfig
import com.dumpstate.feedback.dto.Email

trait EmailService {
  def send(to: Email, subject: String, content: JsObject)(config: PostmarkConfig): Future[Option[Unit]]
}
