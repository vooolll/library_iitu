package controllers

import controllers.auth.Secured
import models.LogTypes
import play.api.mvc._
import services.{LogService, BooksService, SubscriptionService}

object Subscriptions extends Controller with Secured{

  def getSubscribe(bookId: Int) = withUser { user => implicit request =>
    SubscriptionService.subscribe(bookId, user.iituId)
    LogService.add(user.iituId, bookId, LogTypes.SUBSCRIPTION)
    Redirect(routes.Subscriptions.getIndex(bookId))
  }

  def getIndex(bookId: Int) = Action {
    val subscriptions = SubscriptionService.getSubscriptions(bookId)
    Ok(views.html.subscriptions.index(subscriptions._1, subscriptions._2))
  }

  def getQueue = withUser { user => implicit request =>
    val books = SubscriptionService.getQueuesFor(user.iituId)
    Ok(views.html.subscriptions.mine(books))
  }

  def getDelete(subsId: Int) = withUser { user => implicit request =>
    SubscriptionService.delete(subsId)
    val books = SubscriptionService.getQueuesFor(user.iituId)
    Ok(views.html.subscriptions.mine(books))
  }

}
