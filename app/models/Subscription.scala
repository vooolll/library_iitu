package models

import java.sql.Timestamp

case class Subscription(id: Option[Int], book_id: Int, user_id: Int, createdAt: Timestamp)