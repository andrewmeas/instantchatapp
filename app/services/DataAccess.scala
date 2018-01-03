package services

import com.google.inject.{Inject, Singleton}
import play.api.db.Database
import anorm._

@Singleton
class DataAccess @Inject() (db: Database) {

  implicit val parser = Macro.namedParser[Chat]

  def getChats(): List[Chat] = {
    db.withConnection { implicit c =>
      SQL"SELECT * FROM CHAT".as(parser.*)
    }
  }

  def getChat(name: String): List[Chat] = {
    db.withConnection { implicit c =>
      SQL"SELECT * FROM CHAT WHERE name = $name".as(parser.*)
    }
  }

  def createChat(name: String, creator: String): String = {
    db.withConnection { implicit c =>
      SQL"INSERT INTO CHAT (name, creator, date) VALUES($name, $creator, NOW())".execute()
      name
    }
  }

  def deleteChat(name: String) = {
    db.withConnection { implicit c =>
      SQL"DELETE FROM CHAT WHERE name = $name".execute()
    }
  }
}
