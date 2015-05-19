package tables

import java.sql.Timestamp

import models.Subscription

import scala.slick.driver.H2Driver.simple._

class SubscriptionTable(tag: Tag) extends Table[Subscription](tag, "subscriptions") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def bookId = column[Int]("book_id")
  def userId = column[Int]("user_id")
  def createdAt = column[Timestamp]("created_at")
  def status = column[Int]("type")
  def secretCode = column[String]("secret_code", O.Nullable)
  def * = (id.?, bookId, userId, status, createdAt, secretCode.?) <> (Subscription.tupled, Subscription.unapply)
}