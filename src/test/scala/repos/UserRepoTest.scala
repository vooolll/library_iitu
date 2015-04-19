package repos

import models.User
import org.specs2.mutable.Specification

class UserRepoTest extends Specification{

  val me = new User(15493, "vova1992")

  def setUpUser():Unit = {
    UserRepo.delete(15493)
    UserRepo.save(me)
  }

  "UserRepo#get should" >> {
    step(setUpUser())
    "respond with user object" >> {
      UserRepo.get(15493).get equals me
    }
  }
}
