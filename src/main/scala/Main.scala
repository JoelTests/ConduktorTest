import cats.effect.{ExitCode, IO, IOApp}
import com.google.gson.Gson
import common.CTRoot
import producer.PersonProducer
import cats.effect.unsafe.implicits.global

import scala.io.Source

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {

    val jsonString: String = Source.fromResource("random-people-data.json").mkString.stripMargin
    val gson = new Gson
    val ctRoot: CTRoot = gson.fromJson(jsonString, classOf[CTRoot])

    PersonProducer.writePeopleToKafka("people", ctRoot.ctRoot)

    KafkaConsumerServer.server
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
