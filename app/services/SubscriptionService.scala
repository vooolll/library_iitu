package services

import java.sql.Timestamp

import com.steadystate.css.parser.selectors.SubstringAttributeConditionImpl
import models.{SubscriptionTypes, Book, Subscription}
import org.joda.time.DateTime
import repos.{BookRepo, SubscriptionRepo}

object SubscriptionService {

  def subscribe(bookId: Int, userId: Int): Subscription = {
    SubscriptionRepo.subscribe(Subscription(None, bookId, userId, SubscriptionTypes.QUEUE,
      new Timestamp(new DateTime().getMillis)))
  }

  def getSubscriptions(bookId: Int):(List[Subscription], Book) ={
    val subscription = SubscriptionRepo.getSubscriptions(bookId)
    val bookDetails = BookRepo.get(bookId)
    (subscription, bookDetails.get)
  }

  def borrow(subsId: Int): Int = {
    val subscriptionId = SubscriptionRepo.updateStatus(SubscriptionTypes.BORROW, subsId)
    SubscriptionRepo.get(subsId) match {
      case None => subscriptionId
      case Some(subs:Subscription) =>
        BookRepo.subtractQuantity(subs.book_id)
        subscriptionId
    }
  }

  def getAll: List[Subscription] ={
    SubscriptionRepo.all()
  }

  def getQueuesFor(userId: Int): List[Subscription] = {
    println(SubscriptionRepo.getSubscriptionsFor(userId))
    SubscriptionRepo.getSubscriptionsFor(userId)
  }

  def getCode(bookId: Int): Option[String] ={
    //ugly
    SubscriptionRepo.getByBook(bookId).get.secretCode
  }

  def delete(subsId: Int):Unit = {
    SubscriptionRepo.delete(subsId)
  }

  def returnBook(subsId: Int) = {
    SubscriptionRepo.get(subsId) match {
      case None => delete(subsId)
      case Some(subs: Subscription) =>
        BookRepo.addQuantity(subs.book_id)
        delete(subsId)
    }
  }

  def getByCodeAndUser(data: (String, String)): List[Subscription] = {
    SubscriptionRepo.getByCodeAndUser(data)
  }

  def get(subsId: Int): Option[Subscription] = {
    SubscriptionRepo.get(subsId)
  }
}
