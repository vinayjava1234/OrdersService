package services

import Constants.Items
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.stream.Collector
import kotlin.streams.toList

class OrdersService {


//    open fun getTotalCost(items : List<String>) : String? {
//
//        val intItems : List<()->Int?> = items.filter { f -> f != null }.map { str->{
//            getSingleItemCost(str)
//        } }.toList()
//        return "Heloo vinay from Order service"
//
//    }

    open fun getSingleItemCost(item: String): Double{

        if(item.toUpperCase()==Items.ORANGE.toString()){
            return Items.ORANGE.getCostItem().toDouble()
        }else if (item.toUpperCase()==Items.APPLE.toString()){
            return Items.APPLE.getCostItem().toDouble()
        }else{
            return 0f.toDouble();
        }
    }
}