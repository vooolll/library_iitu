package services

import models.Book
import org.specs2.mutable.Specification
import repos.{BookRepo, LogRepo}

class BooksServiceTest extends Specification{

  lazy val book = new Book(None, "Эйсид хаус", 31, "Ирвинг Уэлш")

  def setUpBooks():Unit = {
    BookRepo.deleteAll()
    BookRepo.save(book)

  }

  "BooksService#all should" >> {
    step(setUpBooks())

    "respond with list of available books" >> {
      BooksService.all().head.title equals book.title
    }
  }

}
