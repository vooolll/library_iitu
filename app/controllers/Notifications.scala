package controllers

import controllers.auth.Secured
import play.api.mvc.Controller
import services.NotificationService

object Notifications extends Controller with Secured{

  def getNotifications = withUser { user => request =>
    Ok(views.html.notifications.index(NotificationService.getByUser(user.iituId)))
  }
}
