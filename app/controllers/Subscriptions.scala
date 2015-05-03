package controllers

import controllers.auth.Secured
import play.api.mvc._
import services.SubscriptionService

object Subscriptions extends Controller with Secured{

  def getSubscribe(bookId: Int) = withUser { user => implicit request =>
    SubscriptionService.subscribe(bookId, user.iituId)
    Redirect(routes.Subscriptions.getIndex(bookId))
  }

  def getIndex(bookId: Int) = Action {
    val subscriptions = SubscriptionService.getSubscriptions(bookId)
    Ok(views.html.subscriptions.index(subscriptions._1, subscriptions._2))
  }

}
