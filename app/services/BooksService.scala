package services

import models.Book
import repos.BookRepo

object BooksService {

  def all():List[Book] = {
    BookRepo.getAll
  }

  def create(data: (String, Int, String)):Option[Book] ={
    val book = Book(None, data._1, data._2, data._3)
    BookRepo.save(book)
  }

}