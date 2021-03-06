package com.dumpstate.feedback.service.auth.recaptcha

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Future.successful

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.Uri.Query
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import org.slf4j.Logger

import com.dumpstate.feedback.dto.Secret
import com.dumpstate.feedback.service.auth.AuthService

class RecaptchaAuthServiceImpl(logger: Logger)(
  implicit system: ActorSystem, fm: Materializer) extends AuthService {

  private val http = Http(system)

  private def recaptchaUri(token: String, secret: Secret) =
    Uri("https://www.google.com/recaptcha/api/siteverify").withQuery(
      Query(("secret" -> secret.value), ("response" -> token)))

  override def authenticate(token: String, secret: Secret): Future[Boolean] =
    http.singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = recaptchaUri(token, secret)))
      .flatMap {
        case HttpResponse(StatusCodes.OK, _, res, _) =>
          Unmarshal(res).to[RecaptchaResponse]
            .map(_.success)
            .recoverWith {
              case err =>
                logger.warn(s"Failed to parse response from reCaptcha: $res", err)
                successful(false)
            }
        case response =>
          logger.warn(s"Unauthorized: $response")
          successful(false)
      }
}
