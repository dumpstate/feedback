package com.dumpstate.feedback

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import com.dumpstate.feedback.route.FeedbackRoute

object Boot extends App with FeedbackRoute {
  implicit val system = ActorSystem("feedback-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  Http().bindAndHandle(route, "0.0.0.0", 8080)
}
