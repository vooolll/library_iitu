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
    println("get subscriptions: " + bookDetails)
    (subscription, bookDetails.get)
  }

  def borrow(subsId: Int): Int = {
    val subscription = SubscriptionRepo.updateStatus(SubscriptionTypes.BORROW, subsId)
    subscription
  }

  def getAll: List[Subscription] ={
    SubscriptionRepo.all()
  }

  def getQueuesFor(userId: Int): List[Subscription] = {
    println(SubscriptionRepo.getSubscriptionsFor(userId))
    SubscriptionRepo.getSubscriptionsFor(userId)
  }

}
