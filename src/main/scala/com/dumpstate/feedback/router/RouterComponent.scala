package com.dumpstate.feedback.router

import akka.http.scaladsl.server.Route

trait RouterComponent {
  val route: Route
}
