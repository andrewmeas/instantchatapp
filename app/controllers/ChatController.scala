package controllers

import javax.inject.{Inject, Singleton}

import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.{Chat, DataAccess}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ChatController @Inject()(data: DataAccess, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getChats() = Action.async {
    Future(Ok(Json.toJson(data.getChats())))
  }

  def getChat(name: String) = Action.async {
    Future(Ok(Json.toJson(data.getChat(name))))
  }

  def createChat() = Action.async(parse.json) { request =>
    val body: Chat = request.body.as[Chat]
    val result = data.createChat(body.name, body.creator)
    Future(Ok(result))
  }

  def deleteChat(name: String) =  Action.async {
    Future(Ok(data.deleteChat(name).toString))
  }
}
