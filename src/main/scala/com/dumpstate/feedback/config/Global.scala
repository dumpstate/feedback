package com.dumpstate.feedback.config

import com.typesafe.config.ConfigFactory

class Global() {
  private val conf = ConfigFactory.load()

  val interface = conf.getString("app.interface")
  val port = conf.getInt("app.port")
}
