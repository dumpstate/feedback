package com.dumpstate.feedback.util

import org.slf4j.LoggerFactory

trait LoggerComponent {
  val logger = LoggerFactory.getLogger("feedback")
}
