package consumer

import common.Person
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

import java.time.Duration
import java.util
import java.util.Properties
import scala.jdk.CollectionConverters.*

class PersonConsumer() {
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "consumer.PersonDeserializer")
  val consumer: KafkaConsumer[String, Person] = new KafkaConsumer[String, Person](props)

  def consumePeopleFromKafka(topic: String, offset: Int, count: Int) = {
    val partitions = (offset to offset + count).map(p => new TopicPartition(topic, p))
    consumer.assign(partitions.asJavaCollection)
    val record = consumer.poll(Duration.ofMillis(1000)).asScala
    val peopleIterator = for (data <- record.iterator) yield data.value()
    peopleIterator.toList
  }
}
