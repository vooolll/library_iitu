package models

class Log (id: Option[Int], typeOfAction: Int, user_id: Int, book_id: Int) {
}

object LogTypes{
  val SUBSCRIPTION = 1
}
