package com.dumpstate.feedback.service

import scala.concurrent.Future.successful

import scalaz._, Scalaz._

import com.dumpstate.feedback.dto.input.FeedbackInput
import com.dumpstate.feedback.service.apps.AppsServiceComponent

trait FeedbackServiceComponent
  extends AppsServiceComponent {

  val feedbackService: FeedbackService

  class FeedbackServiceImpl extends FeedbackService {
    override def publish(in: FeedbackInput) =
      appsService.contains(in.appId)
        .option(successful(Right(())))
        .getOrElse(successful(Left(UnknownApplicationId)))
  }
}
