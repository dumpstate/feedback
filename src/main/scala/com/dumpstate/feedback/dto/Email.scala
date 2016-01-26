package com.dumpstate.feedback.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

case class Email(value: String) {
  require(value != null, "value cannot be null")
  require(value.nonEmpty, "value cannot be empty")
}

object Email extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object ApplicationIdFormat extends RootJsonFormat[Email] {
    override def write(email: Email) =
      JsString(email.value)

    override def read(json: JsValue) =
      json match {
        case JsString(email) => Email(email)
        case _ => throw new DeserializationException("ApplicationId expected")
      }
  }
}
