package services

import models.Book
import repos.{SubscriptionRepo, BookRepo}

object BooksService {

  def all():List[Book] = {
    BookRepo.getAll
  }

  def create(data: (String, Int, String)):Option[Book] = {
    val book = Book(None, data._1, data._2, data._3)
    BookRepo.save(book)
  }

  def getBooksFor(userId: Int):List[Book] = {
    SubscriptionRepo.getBorrowSubscription(userId)
  }

  def get(bookId: Int): Option[Book] = {
    BookRepo.get(bookId)
  }

  def getBookByCode(code: String): Option[Book] = {
    val subs = SubscriptionRepo.getByCode(code)
    BookRepo.get(subs.get.book_id)
  }

  def findBook(data: (String, String)): List[Book] = {
    BookRepo.getByCodeAndUser(data)
  }

  def find(data: (String, String, String)): List[Book] = {
    BookRepo.find(data)
  }
}
