package com.dumpstate.feedback.dto

import scalaz._, Scalaz._

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

case class Secret(value: String) {
  require(value != null, "value cannot be null")
}

object Secret extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object SecretFormat extends RootJsonFormat[Secret] {
    override def write(obj: Secret) =
      JsString(obj.value)

    override def read(json: JsValue) =
      json match {
        case JsString(topic) => Secret(topic)
        case _ => throw new DeserializationException("Topic expected")
      }
  }

  implicit val equal = Equal.equalA[Secret]
}
