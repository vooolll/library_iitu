package tables

import models.User
import scala.slick.driver.H2Driver.simple._

class UsersTable(tag: Tag) extends Table[User](tag, "users")  {
  def iitu_id = column[Int]("iitu_id", O.PrimaryKey)
  def password = column[String]("password")
  def * = (iitu_id, password) <> (User.tupled, User.unapply)
}
