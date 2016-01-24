package com.dumpstate.feedback.config.dto

import com.dumpstate.feedback.dto.ApplicationId
import com.typesafe.config.Config

case class AppEntry(id: ApplicationId) {

}

object AppEntry {
  def apply(conf: Config): AppEntry =
    AppEntry(ApplicationId(conf.getString("id")))
}
