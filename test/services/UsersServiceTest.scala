package services

import models.User
import org.specs2.mutable.Specification
import repos.UserRepo

class UsersServiceTest extends Specification{

  val me = new User(15493, "vova1992")

  def setUpUsers():Unit = {
    UserRepo.delete(15493)
    UserRepo.save(me)
  }


  "UserService#all should" >> {
    step(setUpUsers())

    "respond with list of available users" >> {
      UsersService.login((15493, "vova1992")).get equals me
      UsersService.login((15490, "azaaaaza")) equals None
    }
  }

}
