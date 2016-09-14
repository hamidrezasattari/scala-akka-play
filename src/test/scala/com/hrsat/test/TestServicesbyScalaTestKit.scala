package com.hrsat.test




import akka.actor.{ActorSystem, Props}
import akka.testkit.{DefaultTimeout, ImplicitSender, TestKit}
import com.hrsat.actors.PersonInfoProxyStub
import com.hrsat.{PersonInfoRequest, PersonInfoResponse}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import scala.concurrent.duration._
/**
  * Created by hamid on 03/08/16.
  */

class TestServicesbyScalaTestKit
  extends TestKit(ActorSystem(
    "TestServicesbyScalaTestKit",
    ConfigFactory.parseString(TestServicesbyScalaTestKit.config)))
  with DefaultTimeout with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {


  val personInfoActorRef = system.actorOf(Props[PersonInfoProxyStub], "personInfoActor")
  var personInfoRequest = PersonInfoRequest (21);
  val expectedResponse=PersonInfoResponse(1,"John","Spicer","male","a@gmail.com",21,24)

  override def afterAll {
    shutdown()
  }

  "A PersonInfoActor" should {
    "Forward a message it receives" in {
      within(10000 millis) {

        personInfoActorRef ! personInfoRequest
        expectMsg(expectedResponse)
      }
    }
  }





}
//test configuration
object TestServicesbyScalaTestKit {
   val config = """
    akka {
      loglevel = "WARNING"
    }
               """

}


