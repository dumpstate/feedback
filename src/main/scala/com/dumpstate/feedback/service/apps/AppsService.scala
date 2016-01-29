package com.dumpstate.feedback.service.apps

import com.dumpstate.feedback.config.dto.AppEntry
import com.dumpstate.feedback.dto.{Secret, ApplicationId}

trait AppsService {
  def get(appId: ApplicationId): Option[AppEntry]
  def secret(appId: ApplicationId): Option[Secret]
}
