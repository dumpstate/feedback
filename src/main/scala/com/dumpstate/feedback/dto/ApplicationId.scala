package com.dumpstate.feedback.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

case class ApplicationId(value: String) {
  require(value != null, "value cannot be null")
}

object ApplicationId extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object ApplicationIdFormat extends RootJsonFormat[ApplicationId] {
    override def write(appId: ApplicationId) =
      JsString(appId.value)

    override def read(json: JsValue) =
      json match {
        case JsString(appId) => ApplicationId(appId)
        case _ => throw new DeserializationException("ApplicationId expected")
      }
  }
}
