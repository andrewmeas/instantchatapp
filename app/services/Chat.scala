package services

import org.joda.time.DateTime
import play.api.libs.json.Json

object Chat {
  implicit val chatFormat = Json.format[Chat]
}

case class Chat(name: String, creator: String, date: Option[String])
