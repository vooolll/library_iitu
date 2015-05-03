package services

import java.sql.Timestamp

import models.{Book, Subscription}
import org.joda.time.DateTime
import org.specs2.mutable.Specification
import repos.{BookRepo, SubscriptionRepo}

class SubscriptionServiceTest extends Specification{

//  def subscribe(book_id: Int, user_id: Int): Subscription = {
//    SubscriptionRepo.subscribe(Subscription(None, book_id, user_id, new Timestamp(new DateTime().getMillis)))
//  }
val sub = new Subscription(id = None, book_id = 1921, user_id = 15493, createdAt = new Timestamp(new DateTime().getMillis))
val book = new Book(Some(1921), "ASD", 31, "WOW")

  def setUpSubscription():Unit = {
    SubscriptionRepo.deleteAll()
    BookRepo.deleteAll()
    BookRepo.save(book)
    SubscriptionRepo.subscribe(sub)
  }

  "SubscriptionServiceo#get should" >> {
    step(setUpSubscription())
    "respond with sub object" >> {
      SubscriptionService.subscribe(15493, 1921).user_id equals sub.user_id
      SubscriptionService.getSubscriptions(bookId = 1921)._1.head.user_id equals sub.user_id
    }
  }

}
