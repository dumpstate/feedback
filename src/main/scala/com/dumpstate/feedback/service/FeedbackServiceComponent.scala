package com.dumpstate.feedback.service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.successful

import scalaz._, Scalaz._

import spray.json.JsObject

import com.dumpstate.feedback.config.dto.AppEntry
import com.dumpstate.feedback.config.dto.AppEntry.TopicConfig
import com.dumpstate.feedback.dto.input.FeedbackInput
import com.dumpstate.feedback.service.apps.AppsServiceComponent
import com.dumpstate.feedback.service.email.EmailServiceComponent

trait FeedbackServiceComponent
  extends AppsServiceComponent
  with EmailServiceComponent {

  val feedbackService: FeedbackService

  class FeedbackServiceImpl extends FeedbackService {
    private def publish(app: AppEntry, topic: TopicConfig, content: JsObject) =
      emailService.send(topic.recipient, topic.subject, content)(app.postmarkConfig)
        .map(_ => Right(()))

    override def publish(in: FeedbackInput) =
      appsService.get(in.appId)
        .map(app =>
          app.getTopic(in.topic)
            .map(publish(app, _, in.content))
            .getOrElse(successful(Left(UnknownTopic))))
       .getOrElse(successful(Left(UnknownApplicationId)))
  }
}
