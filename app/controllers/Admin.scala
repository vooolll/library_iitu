package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.BooksService

object Admin extends Controller{

  val form = Form(
    tuple(
      "title" -> text(minLength = 4, maxLength = 255),
      "quantity" -> number,
      "author" -> text(minLength = 4, maxLength = 255)
    ))

  def getIndex = Action {
    Ok(views.html.admin.index())
  }

  def getCreate = Action {
    Ok(views.html.admin.create(form))
  }

  def postCreate = Action { implicit request=>
    form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.admin.create(formWithErrors))
      },
      data => {
        BooksService.create(data) match {
          case None =>
            val newForm = form.fill(data).withError("title", "Книга с таким названием уже существует")
            BadRequest(views.html.admin.create(newForm))
          case Some(user) => Redirect(routes.Books.getIndex())
        }
      }
    )
  }

}
