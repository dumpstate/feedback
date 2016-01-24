package com.dumpstate.feedback.service

import scala.concurrent.Future

import com.dumpstate.feedback.dto.input.FeedbackInput

trait FeedbackService {
  def publish(in: FeedbackInput): Future[Either[FeedbackServiceError, Unit]]
}
