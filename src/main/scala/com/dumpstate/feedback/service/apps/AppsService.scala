package com.dumpstate.feedback.service.apps

import com.dumpstate.feedback.dto.ApplicationId

trait AppsService {
  def contains(appId: ApplicationId): Boolean
}
