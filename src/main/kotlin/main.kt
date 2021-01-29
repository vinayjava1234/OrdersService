import Constants.Items
import config.OrderInventory
import kafka.Consumer
import model.Event
import model.Item
import org.apache.kafka.clients.producer.ProducerRecord
import services.MailServiceSubscriber
import services.OrdersService
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.random.Random


@Synchronized fun main() {

    // Initializing Inventory
    val orderInventory= OrderInventory()
    val apple = Item(Items.APPLE.toString(),0.6f.toDouble(),(1+(Math.random()*5).toInt()))
    val orange = Item(Items.ORANGE.toString(),0.25f.toDouble(),(1+(Math.random()*5).toInt()))

    orderInventory.addItem(apple)
    orderInventory.addItem(orange)
    orderInventory.printInventory()
    println("Hi, Please provide the items you want to order?")
    var itemList: MutableList<String> = ArrayList<String>()
    var applesCount: Int = 0
    var orangesCount: Int = 0
    var isOfferReddemed: String? = null

    while (true) {
        val name = readLine()
        if (name.isNullOrEmpty()) {
            break;
        } else {
            if (name.toUpperCase() == Items.APPLE.toString())
                applesCount++
            if (name.toUpperCase() == Items.ORANGE.toString())
                orangesCount++
            itemList.add(name)
        }
    }
    // Checking whether ordered items are eligible for offers
    if (applesCount > 1 && orangesCount > 2) {
        println("Do you want to redeem our offer?")
        println("buy one get one free on Apples")
        println("3 for the price of 2 on Oranges")
        println("Please select, Y/N?")
        isOfferReddemed = readLine()
    } else if (applesCount > 1) {
        println("Do you want to redeem our offer?")
        println("buy one get one free on Apples")
        println("Please select, Y/N?")
        isOfferReddemed = readLine()
    } else if (orangesCount > 2) {
        println("Do you want to redeem our offer?")
        println("3 for the price of 2 on Oranges")
        println("Please select, Y/N?")
        isOfferReddemed = readLine()
    }

    val ordersService = OrdersService()
    ordersService.checkInventoryAndProcessOrder(itemList,(!isOfferReddemed.isNullOrEmpty() && isOfferReddemed == "Y"))

}