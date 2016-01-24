package com.dumpstate.feedback

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import com.dumpstate.feedback.config.Global
import com.dumpstate.feedback.route.FeedbackRoute

object Boot extends App with FeedbackRoute {
  implicit val system = ActorSystem("feedback-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  implicit val conf = new Global

  Http().bindAndHandle(route, conf.interface, conf.port)
}