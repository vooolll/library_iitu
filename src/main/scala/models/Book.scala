package models

import support.Model

case class Book (id: Int, title:String, quantity: Int, author: String) extends Model
