package com.dumpstate.feedback.service.apps

import scalaz._, Scalaz._

import com.dumpstate.feedback.config.ConfigurationComponent
import com.dumpstate.feedback.dto.ApplicationId

trait AppsServiceComponent extends ConfigurationComponent {
  val appsService: AppsService

  class AppsServiceImpl extends AppsService {
    override def get(appId: ApplicationId) =
      config.apps.find(_.id === appId)
  }
}
