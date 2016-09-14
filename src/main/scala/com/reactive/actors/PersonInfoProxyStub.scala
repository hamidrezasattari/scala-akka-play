package com.reactive.actors

import java.util.concurrent.TimeUnit

import akka.actor.ActorLogging
import akka.event.LoggingReceive
import akka.pattern._
import akka.stream.ActorMaterializer
import com.common.{PersonInfoProxy, PersonInfoRequest, PersonInfoResponse}
import play.api.libs.json.JsValue
import play.api.libs.ws.WSRequest
import play.api.libs.ws.ahc.AhcWSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

/**
  * Created by hamid on 03/08/16.
  */
class PersonInfoProxyStub()  extends PersonInfoProxy with ActorLogging {


  implicit val materializer = ActorMaterializer()
 val ws = AhcWSClient()


  def receive = LoggingReceive {
    case PersonInfoRequest(id: Long) =>{
      log.debug(s"Received PersonInfoRequest for ID: $id")



      val wsRequest: WSRequest =
        ws.
          url("http://localhost:8086/personInfo")
          .withHeaders("Accept" -> "application/json")
          .withRequestTimeout(Duration(10, TimeUnit.SECONDS))
          .withQueryString("id" -> "$id")




  val future   =wsRequest.get().map(wsResponse=>{
    readPersonInfo( wsResponse.json)
  })

      pipe(future) to sender()






      //context.stop(self)
    }
//  case class PersonInfoResponse( id:Long,  firstName: String, lastName: String,gender:String,email:String,  settingId: Long, addressId: Long )


      def readPersonInfo(jsonPersonInfo: JsValue) = {

        val id = (jsonPersonInfo \ "id").as[Long]

        val firstName = (jsonPersonInfo \ "firstName").as[String]

        val lastName = (jsonPersonInfo \ "lastName").as[String]

        val gender = (jsonPersonInfo \ "gender").as[String]

        val email = (jsonPersonInfo \ "email").as[String]
        val settingId = (jsonPersonInfo \ "settingId").as[Long]
        val addressId = (jsonPersonInfo \ "addressId").as[Long]

        PersonInfoResponse( id,   firstName , lastName ,gender ,email ,  settingId , addressId  )

      }


  }






}
