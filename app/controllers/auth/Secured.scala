package controllers.auth

import controllers.{Users, routes}
import models.User
import play.api.mvc._
import services.UsersService

trait Secured {

  def username(request: RequestHeader) = request.session.get(Security.username)

  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Users.getSignIn())

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }

  def withUser(f: User => Request[AnyContent] => Result) = withAuth { username => implicit request =>
    UsersService.getUser(username.toInt).map { user =>
      f(user)(request)
    }.getOrElse(onUnauthorized(request))
  }


}
