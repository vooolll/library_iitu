package controllers

import play.api.mvc._
import services.BooksService

object Application extends Controller{

  def getIndex = Action {
    val books = BooksService.all()
    Ok(views.html.index(books))
  }

}