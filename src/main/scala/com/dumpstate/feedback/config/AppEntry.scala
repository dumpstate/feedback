package com.dumpstate.feedback.config

import com.dumpstate.feedback.dto.ApplicationId
import com.typesafe.config.{Config, ConfigObject}

case class AppEntry(id: ApplicationId) {

}

object AppEntry {
  def apply(co: Config): AppEntry =
    AppEntry(ApplicationId(co.getString("id")))
}
