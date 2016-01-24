package com.dumpstate.feedback

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import com.dumpstate.feedback.config.{Configuration, ConfigurationComponent}
import com.dumpstate.feedback.router.{Router, RouterComponent}
import com.dumpstate.feedback.util.LoggerComponent

trait FeedbackComponent
  extends ConfigurationComponent
  with RouterComponent
  with LoggerComponent {

  implicit val system = ActorSystem("feedback-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val globalConfig = ConfigFactory.load()

  override val config = Configuration(globalConfig)
  override val feedbackService = new FeedbackServiceImpl()
  override val appsService = new AppsServiceImpl()
}
