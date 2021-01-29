package services

import Constants.Items
import config.OrderInventory
import kafka.Consumer
import kafka.Producer
import mail.MailServer
import model.Event
import model.Item
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.stream.Collector
import kotlin.streams.toList

class OrdersService {

    val producerInstance = Producer.instance;
    val orderInventory = OrderInventory()


    open fun checkInventoryAndProcessOrder(itemList: MutableList<String>, offer: Boolean): Boolean {
        val orderInventory = OrderInventory()

        val appleList = itemList.filter { item -> item.toUpperCase() == Items.APPLE.toString() };
        val orangeList = itemList.filter { item -> item.toUpperCase() == Items.ORANGE.toString() };

// Starting consumer before starting order process
        startConsumer()

        if (appleList.size > orderInventory.getItemCount(Items.APPLE.toString()) || orangeList.size > orderInventory.getItemCount(
                Items.ORANGE.toString()
            )
        ) {
            publishStockRanOut()
            return false
        } else {
            var reduceInventoryItem = Item(Items.APPLE.toString(), 0f.toDouble(), appleList.size)
            orderInventory.reduceInventory(reduceInventoryItem)
            reduceInventoryItem = Item(Items.ORANGE.toString(), 0f.toDouble(), orangeList.size)
            orderInventory.reduceInventory(reduceInventoryItem)
            //         Publishing order statuses with 5 sec delay
            publishOrderEvents(getTotalCost(itemList, offer))
            return true
        }
    }

    fun startConsumer() {
        val thread = Consumer()
        thread.start()
    }

    fun getTotalCost(itemList: MutableList<String>, offer: Boolean): Double {

        val appleList = itemList.filter { item -> item.toUpperCase() == Items.APPLE.toString() };
        val orangeList = itemList.filter { item -> item.toUpperCase() == Items.ORANGE.toString() };
        if (offer) {
            // calculating orange cost with 2:3 ratio and apple on buy one get one free bases
            return (appleList.toMutableList().subList(0, (appleList.size / 2 + appleList.toMutableList().size % 2))
                .sumByDouble { str -> getSingleItemCost(str) }) + (orangeList.toMutableList()
                .sumByDouble { str -> getSingleItemCost(str) } * 2 / 3)
        } else {
            return itemList.sumByDouble { str -> getSingleItemCost(str) }
        }
    }


    fun publishOrderEvents(total: Double) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        var event: Event = Event("order-submitted", "OrderSubmitted")
        var event1: Event = Event("order-packing", "OrderPacking")
        var event2: Event = Event("order-ready", "OrderReady")
        var event3: Event = Event("order-cost", "${df.format(total)}")
        producerInstance.sendMessage(event)
        Thread.sleep(5000)
        producerInstance.sendMessage(event1)
        Thread.sleep(5000)
        producerInstance.sendMessage(event2)
        Thread.sleep(5000)
        producerInstance.sendMessage(event3)
    }

    fun publishStockRanOut() {
        var event: Event = Event("order-failed", "OutOfOrder")
        producerInstance.sendMessage(event)
    }

    // method to get single item cost
    fun getSingleItemCost(item: String): Double {

        return orderInventory.getItemCost(item.toUpperCase())
    }
}