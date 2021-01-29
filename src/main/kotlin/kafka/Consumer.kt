package kafka

import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration


class Consumer: Thread() {

    public override fun run() {

        val map = mutableMapOf<String, String>()
        map["key.deserializer"]   = "org.apache.kafka.common.serialization.StringDeserializer"
        map["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        map["group.id"] = "test_5"
        map["auto.offset.reset"] = "latest"
        map["bootstrap.servers"] = "localhost:9092"
        val cunsumer= KafkaConsumer<String, String>(map as Map<String, Any>?)
        cunsumer.subscribe(listOf("order-submitted","order-packing","order-ready","order-cost","order-failed"))
        var orderAlive: Boolean = true
        while(orderAlive) {
            val consumerRecords = cunsumer.poll(Duration.ofMillis(5000))
            consumerRecords.records("order-submitted").forEach { record ->
                println(record.value())
            }
            consumerRecords.records("order-packing").forEach { record ->
                println(record.value())
            }
            consumerRecords.records("order-ready").forEach { record ->
                println(record.value())
            }
            consumerRecords.records("order-cost").forEach { record ->
                println("Total Cost $"+record.value())
                orderAlive = false
               cunsumer.commitSync()
            }
            consumerRecords.records("order-failed").forEach { record ->
                println(record.value())
                orderAlive = false
              cunsumer.commitSync()
            }
        }
    }
}