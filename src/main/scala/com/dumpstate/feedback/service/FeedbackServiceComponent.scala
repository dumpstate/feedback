package com.dumpstate.feedback.service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.successful

import scalaz._, Scalaz._

import com.dumpstate.feedback.dto.input.FeedbackInput
import com.dumpstate.feedback.service.apps.AppsServiceComponent
import com.dumpstate.feedback.service.email.EmailServiceComponent

trait FeedbackServiceComponent
  extends AppsServiceComponent
  with EmailServiceComponent {

  val feedbackService: FeedbackService

  class FeedbackServiceImpl extends FeedbackService {
    override def publish(in: FeedbackInput) =
      appsService.get(in.appId)
        .map(app => emailService.send(app.mailerConfig.subject, in)(app.mailerConfig)
          .map(_ => Right(())))
        .getOrElse(successful(Left(UnknownApplicationId)))
  }
}
