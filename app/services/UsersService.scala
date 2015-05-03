package services

import models.User
import repos.UserRepo

object UsersService {

  def login(data: (Int, String)):Option[User] = {
    UserRepo.get(data._1) match {
      case Some(user:User)=> if (user.password == data._2) Some(user) else None
      case None => None
    }
  }

  def getUser(userId: Int): Option[User] ={
    UserRepo.get(userId)
  }

}
