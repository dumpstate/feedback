package com.dumpstate.feedback.service.email.postmark

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

import com.dumpstate.feedback.dto.Email

case class PostmarkEnvelope(
  from: Email,
  to: Email,
  subject: String,
  body: String) {

  require(from != null, "from cannot be null")
  require(to != null, "to cannot be null")
  require(subject != null, "subject cannot be null")
  require(body != null, "body cannot be null")
}

object PostmarkEnvelope extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object PostmarkEnvelopeFormat extends RootJsonFormat[PostmarkEnvelope] {
    override def write(obj: PostmarkEnvelope) =
      JsObject(
        "From" -> JsString(obj.from.value),
        "To" -> JsString(obj.to.value),
        "Subject" -> JsString(obj.subject),
        "HtmlBody" -> JsString(obj.body))

    override def read(json: JsValue) =
      json match {
        case JsObject(List(
          ("From", JsString(from)),
          ("To", JsString(to)),
          ("Subject", JsString(subject)),
          ("HtmlBody", JsString(body)))) =>
          PostmarkEnvelope(Email(from), Email(to), subject, body)
        case _ => throw new DeserializationException("PostmarkEnvelope expected")
      }
  }
}
