package conf

import scala.slick.driver.H2Driver.simple._

object DB {
  lazy val DB = Database.forURL("jdbc:postgresql://127.0.0.1:54320/library", user="homestead",
    password = "secret", driver = "org.postgresql.Driver")
}