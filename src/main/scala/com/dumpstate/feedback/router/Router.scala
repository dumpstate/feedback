package com.dumpstate.feedback.router

import akka.http.scaladsl.server.Route

trait Router {
  val route: Route
}
