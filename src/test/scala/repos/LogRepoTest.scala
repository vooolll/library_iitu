package repos

import org.specs2.mutable.Specification
import models.{LogTypes, Log}
import repos.LogRepo


class LogRepoTest extends Specification{

  val log = new Log(id = None, typeOfAction = LogTypes.SUBSCRIPTION, user_id = 15493, book_id = 1)

  "SubscriptionRepo#get should" >> {

    "respond with sub object" >> {
      LogRepo.all().head.created_at equals log.created_at
    }
  }

}
