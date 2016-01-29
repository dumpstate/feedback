package com.dumpstate.feedback.config.dto

import scala.collection.JavaConversions._

import scalaz._, Scalaz._

import com.typesafe.config.Config

import com.dumpstate.feedback.config.dto.AppEntry.{TopicConfig, PostmarkConfig}
import com.dumpstate.feedback.dto.{Secret, Topic, Email, ApplicationId}

case class AppEntry(
  id: ApplicationId,
  recaptchaSecret: Secret,
  postmarkConfig: PostmarkConfig,
  topics: List[TopicConfig]) {

  require(id != null, "id cannot be null")
  require(recaptchaSecret != null, "recaptchaSecret cannot be null")
  require(postmarkConfig != null, "mailerConfig cannot be null")
  require(topics != null, "topics cannot be null")

  def getTopic(topic: Topic): Option[TopicConfig] =
    topics.find(_.topic === topic)
}

object AppEntry {
  case class PostmarkConfig(sender: Email, token: String) {

    require(sender != null, "sender cannot be null")
    require(token != null, "token cannot be null")
  }

  object PostmarkConfig {
    def apply(conf: Config): PostmarkConfig =
      PostmarkConfig(
        Email(conf.getString("sender")),
        conf.getString("token"))
  }

  case class TopicConfig(topic: Topic, recipient: Email, subject: String) {
    require(topic != null, "topic cannot be null")
    require(recipient != null, "recipient cannot be null")
    require(subject != null, "subject cannot be null")
  }

  object TopicConfig {
    def apply(conf: Config): TopicConfig =
      TopicConfig(
        Topic(conf.getString("id")),
        Email(conf.getString("recipient")),
        conf.getString("subject"))
  }

  def apply(conf: Config): AppEntry =
    AppEntry(
      ApplicationId(conf.getString("id")),
      Secret(conf.getString("recaptcha-secret")),
      PostmarkConfig(conf.getObject("postmark").toConfig),
      conf.getObjectList("topics").toList
        .map(co => TopicConfig(co.toConfig)))
}
