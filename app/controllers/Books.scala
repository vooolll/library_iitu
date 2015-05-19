package controllers

import controllers.auth.Secured
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{ActionTransformer, Action, Controller}
import services.{SubscriptionService, BooksService}


object Books extends Controller with Secured{

  val searchForm =  Form(
    tuple(
      "ID" -> text,
      "Title" -> text,
      "Author" -> text
    ))

  def getIndex = Action {
    val allBooks = BooksService.all()
    Ok(views.html.books.index(allBooks))
  }

  def getCertificate(bookId: Int) = withUser{ user => implicit request =>
    val book = BooksService.get(bookId)
    val secretCode = SubscriptionService.getCode(bookId)
    Ok(views.html.books.cert(book.get, secretCode.get, user))
  }

  def getSearch = withUser { user => implicit request =>
    Ok(views.html.books.find(searchForm, Nil))
  }

  def postSearch = withUser { user => implicit request =>
    searchForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.books.find(formWithErrors, Nil))
      },
      data => {
        if (data._1 forall Character.isDigit) {
          val books = BooksService.find(data)
          Ok(views.html.books.find(searchForm, books))
        } else {
          BadRequest(views.html.books.find(searchForm, Nil))
        }

      }
    )
  }

}
