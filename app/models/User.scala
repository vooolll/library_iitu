package models

import support.Model

case class User(iituId: Int, password: String, role:Int = 1) extends Model