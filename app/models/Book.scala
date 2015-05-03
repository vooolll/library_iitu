package models

import support.Model

case class Book (id: Option[Int], title:String, quantity: Int, author: String) extends Model
