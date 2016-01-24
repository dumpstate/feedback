package com.dumpstate.feedback

import akka.http.scaladsl.Http

object Boot extends App with FeedbackComponent {
  Http().bindAndHandle(route, config.interface, config.port)
}
