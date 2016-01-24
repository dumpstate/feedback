package com.dumpstate.feedback.config

import scala.collection.JavaConversions._

import com.typesafe.config.Config

import com.dumpstate.feedback.config.dto.AppEntry

case class Configuration(
  interface: String,
  port: Int,
  apps: List[AppEntry]) {

  require(interface != null, "interface cannot be null")
  require(apps != null, "apps cannot be null")
}

object Configuration {
  def apply(implicit config: Config): Configuration =
    Configuration(
      interface = config.getString("app.interface"),
      port = config.getInt("app.port"),
      apps = config.getObjectList("registered-applications").toList
        .map(co => AppEntry(co.toConfig)))
}
