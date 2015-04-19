import conf.DB._
import tables.BooksTable
import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.MTable

object Main{
  def main(args: Array[String]) {
    val bookTable = TableQuery[BooksTable]
    DB.withSession{ implicit session =>
      createIfNotExists(bookTable)
    }
    def createIfNotExists(tables: TableQuery[_ <: Table[_]]*)(implicit session: Session) {
      tables foreach {table => if(MTable.getTables(table.baseTableRow.tableName).list.isEmpty) table.ddl.create}
    }

  }
}