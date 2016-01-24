package com.dumpstate.feedback.service.apps

import com.dumpstate.feedback.config.ConfigurationComponent
import com.dumpstate.feedback.dto.ApplicationId

trait AppsServiceComponent extends ConfigurationComponent {
  val appsService: AppsService

  class AppsServiceImpl extends AppsService {
    private val ids = config.apps.map(_.id).toSet

    override def contains(appId: ApplicationId) =
      ids.contains(appId)
  }
}
