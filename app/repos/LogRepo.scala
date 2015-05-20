package repos

import conf.DB._
import models.Log
import tables.LogsTable

import scala.slick.lifted.TableQuery
import scala.slick.driver.H2Driver.simple._

object LogRepo {
  val logTable = TableQuery[LogsTable]

  def all():List[Log] = DB withSession{
    implicit session =>
      logTable.sortBy(_.id.desc).list
  }

  def save(log: Log):Log = DB withSession{
    implicit session =>
      logTable.insert(log)
      log
  }

  def deleteAll(): Int = DB withSession{
    implicit session =>
      logTable.map(l => l).delete
  }

}