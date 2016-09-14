package com.hrsat.test

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.hrsat.PersonInfoRequest
import com.hrsat.actors.PersonInfoProxyStub
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
  * Created by hamid on 03/08/16.
  */
object Main extends App {


  val system = ActorSystem("main")
  val personInfoActorRef = system.actorOf(Props[PersonInfoProxyStub], "personInfoActor");
  var personInfoRequest = PersonInfoRequest(21);
  implicit val timeout: Timeout = Timeout(Duration(10, TimeUnit.SECONDS))

  val response1 = personInfoActorRef ? personInfoRequest

  response1.onComplete { e =>
    Logger.info("This is PersonInfoResponse:" + e)
  }


}

