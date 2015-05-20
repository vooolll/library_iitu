package services

import java.sql.Timestamp

import models.Log
import org.joda.time.DateTime
import repos.LogRepo

object LogService {

  def add(userId: Int, bookId: Int, typeOfAction: Int): Log = {
    LogRepo.save(Log(None, typeOfAction, userId, bookId, new Timestamp(new DateTime().getMillis)))
  }

  def getAll(): List[Log] = {
    LogRepo.all()
  }

}
