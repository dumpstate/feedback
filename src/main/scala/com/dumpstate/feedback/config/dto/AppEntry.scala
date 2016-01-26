package com.dumpstate.feedback.config.dto

import com.typesafe.config.Config

import com.dumpstate.feedback.config.dto.AppEntry.MailerConfig
import com.dumpstate.feedback.dto.{Email, ApplicationId}

case class AppEntry(id: ApplicationId, mailerConfig: MailerConfig) {
  require(id != null, "id cannot be null")
  require(mailerConfig != null, "mailerConfig cannot be null")
}

object AppEntry {
  case class MailerConfig(
    sender: Email,
    recipient: Email,
    subject: String,
    token: String) {

    require(sender != null, "sender cannot be null")
    require(recipient != null, "recipient cannot be null")
    require(subject != null, "subject cannot be null")
    require(token != null, "token cannot be null")
  }

  object MailerConfig {
    def apply(conf: Config): MailerConfig =
      MailerConfig(
        Email(conf.getString("sender")),
        Email(conf.getString("recipient")),
        conf.getString("subject"),
        conf.getString("token"))
  }

  def apply(conf: Config): AppEntry =
    AppEntry(
      ApplicationId(conf.getString("id")),
      MailerConfig(conf.getObject("postmark").toConfig))
}
