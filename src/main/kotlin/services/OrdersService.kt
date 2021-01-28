package services

import Constants.Items
import config.OrderInventory
import mail.MailServer
import model.Event
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.stream.Collector
import kotlin.streams.toList

class OrdersService {

    val serverInstance = MailServer.instance;

    open fun getTotalCost(itemList: MutableList<String>, offer: Boolean): Double {

        val appleList = itemList.filter { item -> item.toUpperCase() == Items.APPLE.toString() };
        val orangeList = itemList.filter { item -> item.toUpperCase() == Items.ORANGE.toString() };
// Publishing order statuses with 5 sec delay
        publishOrderStatuses()

        if (offer) {
            // calculating orange cost with 2:3 ratio and apple on buy one get one free bases
            return (appleList.toMutableList().subList(0, (appleList.size / 2 + appleList.toMutableList().size % 2))
                .sumByDouble { str -> getSingleItemCost(str) }) + (orangeList.toMutableList()
                .sumByDouble { str -> getSingleItemCost(str) } * 2 / 3)
        } else {
            return itemList.sumByDouble { str -> getSingleItemCost(str) }
        }
    }

    open fun checkInventory(itemList: MutableList<String>): Boolean {

        val orderInventory = OrderInventory()
        val appleList = itemList.filter { item -> item.toUpperCase() == Items.APPLE.toString() };
        val orangeList = itemList.filter { item -> item.toUpperCase() == Items.ORANGE.toString() };

        if (appleList.size > orderInventory.getAppleCount() || orangeList.size > orderInventory.getOrangeCount()) {
            publishStockRanOut()
            return false
        } else {
            orderInventory.reduceInventory(appleList.size, orangeList.size)
            return true
        }
    }

    fun publishOrderStatuses() {
        var event: Event = Event("Placed", "Hi, your order has been Placed")
        var event1: Event = Event("Packing", "Hi, packaging your order")
        var event2: Event = Event("Ready", "Hi, your order is ready to pickup")
        serverInstance.sendMessage(event)
        Thread.sleep(5000)
        serverInstance.sendMessage(event1)
        Thread.sleep(5000)
        serverInstance.sendMessage(event2)
    }

    fun publishStockRanOut() {
        var event: Event = Event("NoInventory", "Sorry, Order failed, we are out of items")
        serverInstance.sendMessage(event)
    }

    // method to get single item cost
    fun getSingleItemCost(item: String): Double {

        if (item.toUpperCase() == Items.ORANGE.toString()) {
            return Items.ORANGE.getCostItem().toDouble()
        } else if (item.toUpperCase() == Items.APPLE.toString()) {
            return Items.APPLE.getCostItem().toDouble()
        } else {
            return 0f.toDouble();
        }
    }
}