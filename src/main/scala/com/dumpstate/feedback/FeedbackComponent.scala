package com.dumpstate.feedback

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import com.dumpstate.feedback.config.{Configuration, ConfigurationComponent}
import com.dumpstate.feedback.router.{Router, RouterComponent}

trait FeedbackComponent
  extends ConfigurationComponent
  with RouterComponent {
  implicit val system = ActorSystem("feedback-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val globalConfig = ConfigFactory.load()

  override val config = Configuration(globalConfig)
  override val route = new Router().route
}
