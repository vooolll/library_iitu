package repos

import models.Book
import org.specs2.mutable.Specification

class BookRepoTest extends Specification{
  sequential



  lazy val book = new Book(1, "Эйсид хаус", 31, "Ирвинг Уэлш")

  def clean: Any = {
    BookRepo.deleteAll()
    BookRepo.save(book)

  }

  "BookRepo#save should save " >> {
    step(clean)

    "instance of Book class" >> {
      BookRepo.get(1).get equals book
    }

  }

  "BookRepo#get should respond" >> {
    step(clean)

    "with instance of book class" >> {
      BookRepo.get(1).get equals book
    }
  }

  "BookRepo#delete should respond" >> {
    step(clean)

    "with book id 1" >> {
      BookRepo.delete(1) equals 1
    }
  }

  "BookRepo#update should respond" >> {
    step(clean)

    "with book id 1" >> {
      BookRepo.get(1).get equals book
      BookRepo.update(book, ("Удушье", 12, "Чак паланик")) equals 1
      BookRepo.get(1).get equals new Book(1, "Удушье", 12, "Чак паланик")
    }
  }


}