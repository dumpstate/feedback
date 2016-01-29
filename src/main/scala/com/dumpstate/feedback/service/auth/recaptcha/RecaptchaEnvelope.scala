package com.dumpstate.feedback.service.auth.recaptcha

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

import com.dumpstate.feedback.dto.Secret

case class RecaptchaEnvelope(secret: Secret, response: String) {
  require(secret != null, "secret cannot be null")
  require(response != null, "response cannot be null")
}

object RecaptchaEnvelope extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat2(RecaptchaEnvelope.apply)
}
