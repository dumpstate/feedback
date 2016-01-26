package com.dumpstate.feedback.service.email

import scala.concurrent.Future

import com.dumpstate.feedback.config.dto.AppEntry.MailerConfig
import com.dumpstate.feedback.dto.input.FeedbackInput

trait EmailService {
  def send(subject: String, feedback: FeedbackInput)(config: MailerConfig): Future[Option[Unit]]
}
