package com.dumpstate.feedback.dto

import scalaz._, Scalaz._

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

case class Topic(value: String) {
  require(value != null, "topic cannot be null")
}

object Topic extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object TopicFormat extends RootJsonFormat[Topic] {
    override def write(obj: Topic) =
      JsString(obj.value)

    override def read(json: JsValue) =
      json match {
        case JsString(topic) => Topic(topic)
        case _ => throw new DeserializationException("Topic expected")
      }
  }

  implicit val equal = Equal.equalA[Topic]
}
