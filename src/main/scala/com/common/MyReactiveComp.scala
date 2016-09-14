package com.common

import akka.actor.{Actor, ActorLogging}
import akka.event.LoggingReceive
import play.api.libs.ws.WSRequest

/**
  * Created by hamid on 03/08/16.
  */

  case class PersonInfoResponse( id:Long,  firstName: String, lastName: String,gender:String,email:String,  settingId: Long, addressId: Long )
  case class PersonInfoRequest( id:Long)

  trait PersonInfoProxy extends Actor




