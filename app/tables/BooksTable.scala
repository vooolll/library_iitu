package tables

import models.Book

import scala.slick.driver.H2Driver.simple._


class BooksTable(tag: Tag) extends Table[Book](tag, "books") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def quantity = column[Int]("quantity")
  def author = column[String]("author")
  def * = (id.?, title, quantity, author) <> (Book.tupled, Book.unapply)
}