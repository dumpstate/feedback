package com.dumpstate.feedback.dto.input

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{JsObject, DefaultJsonProtocol}

import com.dumpstate.feedback.dto.ApplicationId

case class FeedbackInput(
  appId: ApplicationId,
  content: JsObject) {

  require(appId != null, "appId cannot be null")
  require(content != null, "content cannot be null")
}

object FeedbackInput extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat2(FeedbackInput.apply)
}
