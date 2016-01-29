package com.dumpstate.feedback.service.auth.recaptcha

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class RecaptchaResponse(success: Boolean)

object RecaptchaResponse extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat1(RecaptchaResponse.apply)
}
