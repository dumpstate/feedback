package com.dumpstate.feedback

import akka.http.scaladsl.Http

object Boot extends App with FeedbackComponent {
  Http().bindAndHandle(route, config.interface, config.port)

  logger.info(s"Server running on ${config.interface}:${config.port}")
}
