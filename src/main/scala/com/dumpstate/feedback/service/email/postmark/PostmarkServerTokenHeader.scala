package com.dumpstate.feedback.service.email.postmark

import akka.http.scaladsl.model.headers.CustomHeader

private[email] case class PostmarkServerTokenHeader(token: String)
  extends CustomHeader {

  override def value() =
    token

  override def name() =
    "X-Postmark-Server-Token"
}
