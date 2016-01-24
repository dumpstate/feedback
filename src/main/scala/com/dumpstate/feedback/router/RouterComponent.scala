package com.dumpstate.feedback.router

import akka.http.scaladsl.server.Directives._
import com.dumpstate.feedback.dto.input.FeedbackInput

trait RouterComponent extends Router {
  override val route =
    path("publish") {
      post {
        decodeRequest {
          entity(as[FeedbackInput]) { input =>
            complete {
              input
            }
          }
        }
      }
    }
}
