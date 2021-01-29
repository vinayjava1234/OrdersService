package kafka


import model.Event
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class Producer {

    var producer :  KafkaProducer<String, String>

    constructor(){
        val map = mutableMapOf<String, String>()
        map["key.serializer"]   = "org.apache.kafka.common.serialization.StringSerializer"
        map["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        map["bootstrap.servers"] = "localhost:9092"

        this.producer = KafkaProducer<String, String>(map as Map<String, Any>?)
    }


    companion object {
        var instance: Producer = Producer()
    }

    open fun sendMessage(event: Event) {
        var producerRecord: ProducerRecord<String,String> = ProducerRecord(event.eventType,event.message)
        this.producer.send(producerRecord)
    }


}