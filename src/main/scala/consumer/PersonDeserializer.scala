package consumer

import com.fasterxml.jackson.databind.ObjectMapper
import common.Person
import org.apache.kafka.common.serialization.Deserializer

class PersonDeserializer extends Deserializer[Person] {
  override def deserialize(s: String, bytes: Array[Byte]): Person = {
    val mapper = new ObjectMapper()
    val person = mapper.readValue(bytes, classOf[Person])
    person
  }
}
