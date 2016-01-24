package com.dumpstate.feedback.config

import scala.collection.JavaConversions._

import com.typesafe.config.ConfigFactory

class Global() {
  private val conf = ConfigFactory.load()

  val interface = conf.getString("app.interface")
  val port = conf.getInt("app.port")
  val apps = conf.getObjectList("registered-applications")
    .toList
    .map(co => AppEntry(co.toConfig))
}
