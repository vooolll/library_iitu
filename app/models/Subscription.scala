package models

import java.sql.Timestamp

case class Subscription(id: Option[Int], book_id: Int, user_id: Int,
                        status: Int = SubscriptionTypes.QUEUE, createdAt: Timestamp, secretCode: Option[String] = None)

object SubscriptionTypes {
  val QUEUE = 1
  val BORROW = 2
}