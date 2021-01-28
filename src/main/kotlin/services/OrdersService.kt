package services

import Constants.Items
import mail.MailServer
import model.Event
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.stream.Collector
import kotlin.streams.toList

class OrdersService {

    val serverInstance = MailServer.instance;

    open fun getTotalCost(itemList: MutableList<String>, offer: Boolean): Double {
// Publishing order statuses with 5 sec delay
        publishOrderStatuses()

        if (offer) {
            var appleList: MutableList<String> =
                itemList.filter { item -> item.toUpperCase() == Items.APPLE.toString() }.toMutableList();
            val orangeList: MutableList<String> =
                itemList.filter { item -> item.toUpperCase() == Items.ORANGE.toString() }.toMutableList();
     // calculating orange cost with 2:3 ratio and apple on buy one get one free bases
            return (appleList.subList(0, (appleList.size / 2 + appleList.size % 2))
                .sumByDouble { str -> getSingleItemCost(str) }) + (orangeList.sumByDouble { str -> getSingleItemCost(str) } * 2 / 3)
        } else {
            return itemList.sumByDouble { str -> getSingleItemCost(str) }
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