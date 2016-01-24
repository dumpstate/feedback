package com.dumpstate.feedback.service

import scala.concurrent.Future.successful

import com.dumpstate.feedback.dto.input.FeedbackInput

trait FeedbackServiceComponent {
  val feedbackService: FeedbackService

  class FeedbackServiceImpl extends FeedbackService {
    override def publish(in: FeedbackInput) =
      successful(Right(()))
  }
}
