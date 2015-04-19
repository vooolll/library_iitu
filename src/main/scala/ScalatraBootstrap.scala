import javax.servlet.ServletContext
import org.scalatra.{ScalatraServlet, LifeCycle}

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {

//    route(new Organizations, "/signup/*")

    def route(servlet: ScalatraServlet, url: String): Unit = {
      context.mount(servlet, url)
    }
  }
}


