package services

import java.sql.Timestamp

import models.{Book, Subscription}
import org.joda.time.DateTime
import repos.{BookRepo, SubscriptionRepo}

object SubscriptionService {

  def subscribe(bookId: Int, userId: Int): Subscription = {
    SubscriptionRepo.subscribe(Subscription(None, bookId, userId, new Timestamp(new DateTime().getMillis)))
  }

  def getSubscriptions(bookId: Int):(List[Subscription], Book) ={
    val subscription = SubscriptionRepo.getSubscriptions(bookId)
    val bookDetails = BookRepo.get(bookId)
    println("get subscriptions: " + bookDetails)
    (subscription, bookDetails.get)
  }

}
