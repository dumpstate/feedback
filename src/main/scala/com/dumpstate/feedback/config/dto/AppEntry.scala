package com.dumpstate.feedback.config.dto

import com.dumpstate.feedback.dto.ApplicationId
import com.typesafe.config.Config

case class AppEntry(id: ApplicationId) {

}

object AppEntry {
  def apply(co: Config): AppEntry =
    AppEntry(ApplicationId(co.getString("id")))
}
