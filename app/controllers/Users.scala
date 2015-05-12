package controllers

import controllers.auth.Secured
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import services.{BooksService, UsersService}


object Users extends Controller with Secured{

  val form = Form(
    tuple(
      "ID" -> number(min = 10000),
      "Password" -> text(minLength = 6, maxLength = 25)
    ))

  def getSignIn = Action {
    Ok(views.html.signin(form))
  }

  def postSignIn = Action { implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.signin(formWithErrors))
      },
      data => {
        UsersService.login(data) match {
          case Some(user) => if (user.role == 1) Redirect(routes.Application.getIndex())
              .withSession(Security.username -> user.iituId.toString)
            else Redirect(routes.Admin.getIndex())
              .withSession(Security.username -> user.iituId.toString)
              .withSession("role" -> "2")

          case None =>
            val formWithError = form.fill(data).withError("ID", "Не верный ID или пароль")
            BadRequest(views.html.signin(formWithError))
        }
      }
    )
  }

  def getProfile = withUser { user => implicit request =>
    val books = BooksService.getBooksFor(user.iituId)
    Ok(views.html.books.mine(books))
  }
}