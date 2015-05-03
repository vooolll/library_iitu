package tables

import models.User
import scala.slick.driver.H2Driver.simple._

class UsersTable(tag: Tag) extends Table[User](tag, "users")  {
  def iitu_id = column[Int]("iitu_id", O.PrimaryKey)
  def password = column[String]("password")
  def role = column[Int]("role", O.Default(1))
  def * = (iitu_id, password, role) <> (User.tupled, User.unapply)
}
