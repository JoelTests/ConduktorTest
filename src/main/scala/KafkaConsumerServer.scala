import consumer.PersonConsumer
import org.http4s.*
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory
import cats.effect._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.blaze.server.BlazeServerBuilder

object KafkaConsumerServer {
  implicit val loggerFactory: LoggerFactory[IO] = Slf4jFactory.create[IO]

  val personConsumer = PersonConsumer()

  val app = HttpRoutes.of[IO] {
    case r@GET -> Root / "topic" / topicName / offset if r.params.isEmpty =>
      Ok(personConsumer.consumePeopleFromKafka(topicName, offset.toInt, 1).toString())
    case r@GET -> Root / "topic" / topicName / offset =>
      Ok(personConsumer.consumePeopleFromKafka(topicName, offset.toInt, r.params.getOrElse("count", "1").toInt).toString())
  }.orNotFound

  val server = BlazeServerBuilder[IO]
    .bindHttp(8080, "localhost")
    .withHttpApp(app)
}