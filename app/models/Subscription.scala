package models

import java.sql.Timestamp

case class Subscription(id: Option[Int], book_id: Int, user_id: Int, status: Int = 1, createdAt: Timestamp)

object SubscriptionTypes {
  val QUEUE = 1
  val BORROW = 2
}