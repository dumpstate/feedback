package com.dumpstate.feedback

import akka.actor.Actor
import spray.http._
import spray.routing._
import MediaTypes._

class FeedbackActor extends Actor with Feedback {
  def actorRefFactory = context

  def receive = runRoute(route)
}

trait Feedback extends HttpService {
  val route =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>Yellow!</h1>
              </body>
            </html>
          }
        }
      }
    }
}
