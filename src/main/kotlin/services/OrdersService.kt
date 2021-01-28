package services

import Constants.Items
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.stream.Collector
import kotlin.streams.toList

class OrdersService {


    open fun getTotalCost(itemList: MutableList<String>) : Double {

        return itemList.sumByDouble{ str-> getSingleItemCost(str)}

    }

     fun getSingleItemCost(item: String): Double{

        if(item.toUpperCase()==Items.ORANGE.toString()){
            return Items.ORANGE.getCostItem().toDouble()
        }else if (item.toUpperCase()==Items.APPLE.toString()){
            return Items.APPLE.getCostItem().toDouble()
        }else{
            return 0f.toDouble();
        }
    }
}