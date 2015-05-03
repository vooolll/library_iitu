package controllers

import play.api.mvc._

object Application extends Controller{

  def getIndex = Action {
    Ok(views.html.index())
  }

}