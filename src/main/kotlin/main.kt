import Constants.Items
import services.OrdersService
import java.math.RoundingMode
import java.text.DecimalFormat

fun main() {
    println("Hi, Please provide the items you want to order?")
    var itemList: MutableList<String> = ArrayList<String>()
    while(true){
        val name= readLine()
        if(name.isNullOrEmpty()){
            break;
        }else{
            itemList.add(name)
        }
    }
    val ordersService = OrdersService()
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN

    println("Total cost of the Items is ${df.format(ordersService.getTotalCost(itemList))}$");

}