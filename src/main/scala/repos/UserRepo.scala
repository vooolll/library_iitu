package repos

import conf.DB._
import support.{Model, Repo}
import models.User
import tables.UsersTable
import scala.slick.driver.H2Driver.simple._

import scala.slick.lifted.TableQuery

object UserRepo extends Repo{
  val userTable = TableQuery[UsersTable]

  def get(id: Int): Option[User] = DB withSession{
    implicit session =>
      userTable.filter(_.iitu_id === id).firstOption
  }

  def save(user: User): User = DB withSession {
    implicit session =>
      userTable.insert(user)
      user
  }

  def delete(id: Int):Int = DB withSession {
    implicit session =>
      userTable.filter(_.iitu_id === id).delete
  }

}
