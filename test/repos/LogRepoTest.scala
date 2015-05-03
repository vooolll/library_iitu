package repos

import java.sql.Timestamp

import org.joda.time.DateTime
import org.specs2.mutable.Specification
import models.{LogTypes, Log}


class LogRepoTest extends Specification{

  val log = new Log(id = None, typeOfAction = LogTypes.SUBSCRIPTION, user_id = 15493, book_id = 1,
    createdAt = new Timestamp(new DateTime().getMillis))


  def setUpLog():Unit = {
    LogRepo.deleteAll()
    LogRepo.save(log)
  }

  "Log#get should" >> {
    step(setUpLog())

    "respond with log object" >> {
      LogRepo.all().head.createdAt equals log.createdAt
    }
  }

}