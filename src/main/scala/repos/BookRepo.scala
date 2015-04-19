package repos
import models.Book
import conf.DB._
import support.{Model, Repo}
import tables.BooksTable
import scala.slick.driver.H2Driver.simple._

object BookRepo extends Repo{

  val bookTable = TableQuery[BooksTable]

  def save(book: Book): Book = {
    DB withSession {
      implicit session =>
        if (!exists(book)) bookTable.insert(book)
        None
    }
    book
  }

  def getAll: List[Book] = DB withSession{
    implicit session =>
      bookTable.run.toList
  }

  def exists(book: Book): Boolean = DB withSession {
    implicit session =>
      bookTable.filter(_.id === book.id).firstOption != None
  }

  def get(id: Int): Option[Book] = DB withSession {
    implicit session =>
      bookTable.filter(_.id === id).firstOption
  }

  def delete(id: Int): Int = DB withSession {
    implicit session =>
      bookTable.filter(_.id === id).delete
  }

  def update(book: Book, data: (String, Int, String)):Int = DB withSession {
    implicit session =>
      bookTable.filter(_.id === book.id).map(b => (b.title, b.quantity, b.author)).update(data)
  }

  def deleteAll(): Int = DB withSession {
    implicit session =>
      bookTable.map(b => b).delete
  }

}