package com.dumpstate.feedback

import scala.concurrent.duration._

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

object Boot extends App {
  implicit val system = ActorSystem("feedback-system")

  val service = system.actorOf(Props[FeedbackActor], "feedback")

  implicit val timeout = Timeout(5.seconds)

  IO(Http) ? Http.Bind(service, interface = "0.0.0.0", port = 8080)
}
