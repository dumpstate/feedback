package com.dumpstate.feedback.router

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.successful
import scala.util.{Failure => TFailure, Success => TSuccess}

import scalaz._, Scalaz._

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._

import com.dumpstate.feedback.dto.input.FeedbackInput
import com.dumpstate.feedback.service._
import com.dumpstate.feedback.service.auth.AuthServiceComponent
import com.dumpstate.feedback.util.LoggerComponent

trait RouterComponent extends Router
  with FeedbackServiceComponent
  with LoggerComponent
  with AuthServiceComponent {

  private def publishError(err: FeedbackServiceError) =
    err match {
      case UnknownApplicationId =>
        complete(NotFound)
      case UnknownTopic =>
        complete(UnprocessableEntity)
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

  def authenticateAndPublish(token: String, in: FeedbackInput) =
    authService
      .authenticate(token)
      .flatMap(_.option(publish(in))
        .getOrElse(successful(complete(Unauthorized))))

  override val route =
    path("publish") {
      post {
        decodeRequest {
          extractCredentials(
            _.map(creds => entity(as[FeedbackInput])(in =>
              onComplete(authenticateAndPublish(creds.scheme, in)) {
                case TSuccess(res) => res
                case TFailure(ex) =>
                  complete(InternalServerError)
              }))
             .getOrElse(complete(Unauthorized)))
        }
      }
    }
}
