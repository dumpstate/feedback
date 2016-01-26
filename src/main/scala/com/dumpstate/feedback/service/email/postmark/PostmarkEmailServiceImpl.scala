package com.dumpstate.feedback.service.email.postmark

import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.Accept
import akka.stream.Materializer
import org.slf4j.Logger
import spray.json._

import com.dumpstate.feedback.config.dto.AppEntry.MailerConfig
import com.dumpstate.feedback.dto.input.FeedbackInput
import com.dumpstate.feedback.service.email.EmailService

class PostmarkEmailServiceImpl(logger: Logger)(
  implicit system: ActorSystem, fm: Materializer) extends EmailService {

  val http = Http(system)

  def sendMailRequest(token: String) =
    HttpRequest(
      method = HttpMethods.POST,
      uri = "https://api.postmarkapp.com/email",
      headers = List(
        Accept(MediaTypes.`application/json`),
        PostmarkServerTokenHeader(token)))

  override def send(subject: String, feedback: FeedbackInput)(config: MailerConfig) =
    http.singleRequest(
      sendMailRequest(config.token)
        .withEntity(
          ContentTypes.`application/json`,
          PostmarkEnvelope(config.sender, config.recipient,
            subject, feedback.toJson.prettyPrint).toJson.compactPrint))
      .map {
        case HttpResponse(StatusCodes.OK, _, _, _) => Some(())
        case response =>
          logger.error(s"Failed to send message: $response")
          None
      }
}
