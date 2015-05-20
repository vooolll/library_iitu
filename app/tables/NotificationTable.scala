package tables
import models.Notification
import scala.slick.driver.H2Driver.simple._

class NotificationTable(tag: Tag) extends Table[Notification](tag, "notifications") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def userId = column[Int]("user_id")
  def message = column[String]("message")
  def * = (id.?, message, userId) <> (Notification.tupled, Notification.unapply)
}
