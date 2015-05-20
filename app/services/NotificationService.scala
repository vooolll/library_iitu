package services

import models.Notification
import repos.NotificationRepo

object NotificationService {
  def add(userId: Int, message: String): Notification = {
    NotificationRepo.add(Notification(None, message, userId))
  }

  def getByUser(userId: Int): List[Notification] = {
    NotificationRepo.getForUser(userId)
  }
}
