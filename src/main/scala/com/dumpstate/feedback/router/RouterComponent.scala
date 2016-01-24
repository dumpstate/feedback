package com.dumpstate.feedback.router

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._

import com.dumpstate.feedback.dto.input.FeedbackInput
import com.dumpstate.feedback.service._
import com.dumpstate.feedback.util.LoggerComponent

trait RouterComponent extends Router
  with FeedbackServiceComponent
  with LoggerComponent {

  private def publishError(err: FeedbackServiceError) =
    err match {
      case UnknownApplicationId =>
        complete(NotFound)
      case ThirdPartyServiceNotAvailable =>
        complete(ServiceUnavailable)
    }

  private def publish(in: FeedbackInput) =
    feedbackService.publish(in)
      .map {
        case Left(err) =>
          publishError(err)
        case Right(_) =>
          complete(NoContent)
      }

  override val route =
    path("publish") {
      post {
        decodeRequest {
          entity(as[FeedbackInput]) { in =>
            onComplete(publish(in)) {
              case Success(res) => res
              case Failure(ex) =>
                logger.error(s"Error occured: ${ex.getMessage}")
                complete(InternalServerError)
            }
          }
        }
      }
    }
}
