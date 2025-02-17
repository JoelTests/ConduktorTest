package producer

import common.Person

import java.util.Properties
import org.apache.kafka.clients.producer.*
import org.apache.kafka.common.serialization.{Deserializer, Serializer}

object PersonProducer {
  def writePeopleToKafka(topic: String, people: Array[Person]): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "producer.PersonSerializer")
    val producer = new KafkaProducer[String, Person](props)
    for (person <- people) {
      val record = new ProducerRecord[String, Person](topic, person._id, person)
      producer.send(record)
    }
    producer.close()
  }
}
