package controllers

import play.api.mvc.{Action, Controller}
import services.BooksService

object Books extends Controller{

  def getIndex = Action {
    val allBooks = BooksService.all()
    Ok(views.html.books.index(allBooks))
  }
}
