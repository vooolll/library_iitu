package models

import java.sql.Timestamp

case class Log (id: Option[Int], typeOfAction: Int, user_id: Int, book_id: Int, createdAt: Timestamp)

object LogTypes{
  val SUBSCRIPTION = 1
  val BORROW = 2
  val RETURN = 3
}