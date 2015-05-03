package repos

import java.sql.Timestamp

import models.Subscription
import org.joda.time._
import org.specs2.mutable.Specification

class SubscriptionRepoTest extends Specification{
  val sub = new Subscription(id = None, book_id = 1, user_id = 15493, createdAt = new Timestamp(new DateTime().getMillis))

  def setUpSubscription():Unit = {
    SubscriptionRepo.deleteAll()
    SubscriptionRepo.subscribe(sub)
  }

  "SubscriptionRepo#get should" >> {
    step(setUpSubscription())
    "respond with sub object" >> {
      SubscriptionRepo.all().head.createdAt equals sub.createdAt
    }
  }

  "SubscriptionRepo#getSubscriptions should" >> {
    step(setUpSubscription())
    "respond with list of sub object" >> {
      SubscriptionRepo.getSubscriptions(1).head.user_id equals sub.user_id
    }
  }

}
