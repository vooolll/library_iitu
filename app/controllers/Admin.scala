package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.{SubscriptionService, BooksService}

object Admin extends Controller{

  val form = Form(
    tuple(
      "title" -> text(minLength = 4, maxLength = 255),
      "quantity" -> number,
      "author" -> text(minLength = 4, maxLength = 255)
    ))

  def getIndex = Action {
    val books = BooksService.all()
    Ok(views.html.admin.books.index(books))
  }

  def getCreate = Action {
    Ok(views.html.admin.books.create(form))
  }

  def postCreate = Action { implicit request=>
    form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.admin.books.create(formWithErrors))
      },
      data => {
        BooksService.create(data) match {
          case None =>
            val newForm = form.fill(data).withError("title", "Книга с таким названием уже существует")
            BadRequest(views.html.admin.books.create(newForm))
          case Some(user) => Redirect(routes.Admin.getIndex())
        }
      }
    )
  }

  def getAllSubscriptions = Action {
    val allSubscriptions = SubscriptionService.getAll
    Ok(views.html.admin.subscriptions.index(allSubscriptions))
  }

  def getSubscriptions(bookId: Int) = Action {
    val bookSubs = SubscriptionService.getSubscriptions(bookId)
    Ok(views.html.admin.subscriptions.book_subs(bookSubs._1, bookSubs._2))
  }

  def getBorrow(subsId: Int) = Action {
    SubscriptionService.borrow(subsId)
    Redirect(routes.Admin.getAllSubscriptions())
  }

}
