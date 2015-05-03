package repos

import models.Subscription
import support.Repo
import conf.DB._
import tables.SubscriptionTable
import scala.slick.driver.H2Driver.simple._

object SubscriptionRepo extends Repo{

  val subsTable = TableQuery[SubscriptionTable]

  def subscribe(subscription: Subscription): Subscription = DB withSession {
    implicit session =>
      subsTable.insert(subscription)
      subscription
  }

  def all(): List[Subscription] = DB withSession {
    implicit session =>
      subsTable.run.toList
  }

  def deleteAll():Int = DB withSession {
    implicit session =>
      subsTable.map(b => b).delete
  }

  def getSubscriptions(bookId: Int): List[Subscription] = DB withSession {
    implicit session =>
      subsTable.filter(s => s.bookId === bookId).list
  }
}

