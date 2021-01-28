import Constants.Items
import model.Event
import services.MailServiceSubscriber
import services.OrdersService
import java.math.RoundingMode
import java.text.DecimalFormat

fun main() {
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
// getting information from user about notifications
    println("Are you willing to get notified about your order status? Y/N")
    var getNotified = readLine()
    if(getNotified == "Y") {
        val subscriber: MailServiceSubscriber = MailServiceSubscriber()
    }

    val ordersService = OrdersService()
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    if (!isOfferReddemed.isNullOrEmpty() && isOfferReddemed == "N") {
        println("Total cost of the Items is ${df.format(ordersService.getTotalCost(itemList, false))}$");
    } else {
        println("Total cost of the Items is ${df.format(ordersService.getTotalCost(itemList, true))}$");
    }


}