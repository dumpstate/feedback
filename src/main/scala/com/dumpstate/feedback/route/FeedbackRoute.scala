package com.dumpstate.feedback.route

import akka.http.scaladsl.server.Directives._

import com.dumpstate.feedback.dto.input.FeedbackInput

trait FeedbackRoute {
  val route =
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
