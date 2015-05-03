package tables

import java.sql.Timestamp

import scala.slick.driver.H2Driver.simple._
import models.Log

class LogsTable(tag: Tag) extends Table[Log](tag, "logs") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def typeOfAction = column[Int]("action")
  def userId = column[Int]("user_id")
  def bookId = column[Int]("book_id")
  def createdAt = column[Timestamp]("created_at")
  def * = (id.?, typeOfAction, userId, bookId, createdAt) <> (Log.tupled, Log.unapply)
}