package com.dumpstate.feedback.service.auth.recaptcha

import akka.util.ByteString

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Future.successful
import scala.util.control.Exception.catching

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.Materializer
import org.slf4j.Logger
import spray.json._

import com.dumpstate.feedback.dto.Secret
import com.dumpstate.feedback.service.auth.AuthService

class RecaptchaAuthServiceImpl(logger: Logger)(
  implicit system: ActorSystem, fm: Materializer) extends AuthService {

  val http = Http(system)

  override def authenticate(token: String, secret: Secret): Future[Boolean] =
    http.singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = "https://www.google.com/recaptcha/api/sireverify")
        .withEntity(
          ContentTypes.`application/json`,
          RecaptchaEnvelope(secret, token).toJson.compactPrint))
      .flatMap {
        case HttpResponse(StatusCodes.OK, _, res, _) =>
          catching(classOf[DeserializationException]).opt(
            res.dataBytes.runFold(ByteString(""))(_ ++ _)
              .map(_.utf8String.parseJson
                .convertTo[RecaptchaResponse].success))
            .getOrElse(successful(false))
        case response =>
          logger.warn(s"Unauthorized: $response")
          successful(false)
      }
}
