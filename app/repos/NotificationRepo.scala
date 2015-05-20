package repos

import conf.DB._
import models.Notification
import tables.NotificationTable

import scala.slick.driver.H2Driver.simple._

object NotificationRepo {

  val notifTable = TableQuery[NotificationTable]

  def add(notification: Notification):Notification = DB withSession{
    implicit session =>
      notifTable.insert(notification)
      notification
  }

  def getForUser(userId: Int): List[Notification] = DB withSession {
    implicit session =>
      notifTable.filter(_.userId === userId).list
  }

}
