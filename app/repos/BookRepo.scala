package repos
import models.Book
import conf.DB._
import support.{Model, Repo}
import tables.{SubscriptionTable, BooksTable}
import scala.slick.driver.H2Driver.simple._

object BookRepo extends Repo{

  val bookTable = TableQuery[BooksTable]
  val subsTable = TableQuery[SubscriptionTable]

  def save(book: Book): Option[Book] = DB withSession {
    implicit session =>
      if (!exists(book)) {
        bookTable.insert(book)
        Some(book)
      }
      else None
  }

  def getAll: List[Book] = DB withSession{
    implicit session =>
      bookTable.run.toList
  }

  def exists(book: Book): Boolean = DB withSession {
    implicit session =>
      bookTable.filter(_.title === book.title).firstOption != None
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

  def find(data: (String, String, String)): List[Book] = DB withSession {
    implicit session =>
      data match {
        case ("", _, "") => bookTable.filter(b => b.title.like("%" + data._2 +"%")).list
        case ("", "", _) => bookTable.filter(b => b.author.like("%" + data._3 +"%")).list
        case ("", _, _) => bookTable.filter(b => b.title.like("%" + data._2 +"%") && b.author.like("%" + data._2 + "%")).list
        case _ => bookTable.filter(_.id === data._1.toInt).list
      }
  }

  def subtractQuantity(bookId: Int): Int = DB withSession{
    implicit session =>
      val quantity = bookTable.filter(_.id === bookId).map(b => b.quantity).firstOption
      bookTable.filter(_.id === bookId).map(b => b.quantity).update(quantity.get - 1)
  }

  def getByCodeAndUser(data: (String, String)):List[Book] = DB withSession {
    implicit session =>
      val ids = data match {
        case ("", "") => subsTable.filter(_.secretCode === data._1).map(_.bookId)
        case ("", userId) => subsTable.filter(_.userId === data._2.toInt).map(_.bookId)
        case (code, "") => subsTable.filter(_.secretCode === data._1).map(_.bookId)

        case (code, userId) => subsTable.filter(s => s.secretCode === code && s.userId === data._2.toInt).map(_.bookId)
      }
      bookTable.filter(_.id in ids).list
  }

}