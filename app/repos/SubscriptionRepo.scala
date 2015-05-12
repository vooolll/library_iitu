package repos

import models.{Book, SubscriptionTypes, Subscription}
import support.Repo
import conf.DB._
import tables.{BooksTable, SubscriptionTable}
import scala.slick.driver.H2Driver.simple._

object SubscriptionRepo extends Repo{

  val subsTable = TableQuery[SubscriptionTable]
  val bookTable = TableQuery[BooksTable]

  def subscribe(subscription: Subscription): Subscription = DB withSession {
    implicit session =>
      if (!isSubscriptionExist(subscription)) subsTable.insert(subscription)
      subscription
  }

  def isSubscriptionExist(subscription: Subscription):Boolean = DB withSession{
    implicit session =>
      subsTable.filter(
        subs => subs.bookId === subscription.book_id &&
                subs.userId === subscription.user_id
      ).firstOption != None
  }

  def all(): List[Subscription] = DB withSession {
    implicit session =>
      subsTable.filter(s=> s.status === SubscriptionTypes.QUEUE).list
  }

  def deleteAll():Int = DB withSession {
    implicit session =>
      subsTable.map(b => b).delete
  }

  def getSubscriptions(bookId: Int): List[Subscription] = DB withSession {
    implicit session =>
      subsTable.filter(s => s.bookId === bookId && s.status === SubscriptionTypes.QUEUE).list
  }

  def updateStatus(status: Int, subscriptionId: Int): Int = DB withSession {
    implicit session =>
      subsTable.filter(_.id === subscriptionId).map(s => s.status).update(status)
  }

  def getBorrowSubscription(userId: Int):List[Book] = DB withSession {
    implicit session =>
      val ids = subsTable.filter(s => s.userId === userId && s.status === SubscriptionTypes.BORROW).map(_.bookId)
      bookTable.filter(_.id in ids).list
  }

  def getSubscriptionsFor(userId: Int): List[Subscription] = DB withSession{
    implicit session =>
      val query = subsTable.filter(s => s.userId === userId && s.status === SubscriptionTypes.QUEUE).sortBy(_.id)
      query.list
  }
}

