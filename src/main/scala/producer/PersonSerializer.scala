package producer

import com.fasterxml.jackson.databind.ObjectMapper
import common.Person
import org.apache.kafka.common.serialization.Serializer

class PersonSerializer extends Serializer[Person] {
  override def serialize(topic: String, data: Person): Array[Byte] = {
    if (data == null)
      null
    else {
      val objectMapper = new ObjectMapper()
      objectMapper.writeValueAsString(data).getBytes
    }
  }
}
