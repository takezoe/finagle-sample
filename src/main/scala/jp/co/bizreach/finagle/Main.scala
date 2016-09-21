import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http._
import com.twitter.finagle.http.path._
import com.twitter.finagle.http.service.RoutingService
import com.twitter.io.Buf.Utf8
import com.twitter.io.Reader
import com.twitter.util.{Await, Future}

object Main extends App {

  val router = RoutingService.byPathObject[Request] {
    case Root => new Service[Request, Response] {
      def apply(req: Request): Future[Response] = {
        Future(
          Response(req.version, Status.Ok, Reader.fromBuf(Utf8("API is ready.")))
        )
      }
    }
    case Root / "hello"/ name => new Service[Request, Response] {
      def apply(req: Request): Future[Response] = {
        Future(
          Response(req.version, Status.Ok, Reader.fromBuf(Utf8(s"Hello ${name}.")))
        )
      }
    }
  }

  val server = Http.serve(":8080", router)
  Await.ready(server)

}
