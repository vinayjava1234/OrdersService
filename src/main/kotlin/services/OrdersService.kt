package services

import Constants.Items
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.stream.Collector
import kotlin.streams.toList

class OrdersService {



    open fun getTotalCost(itemList: MutableList<String>, offer: Boolean) : Double {
        if(offer){
            var appleList: MutableList<String> = itemList.filter { item-> item.toUpperCase()==Items.APPLE.toString() }.toMutableList();
            val orrangeList: MutableList<String> = itemList.filter { item-> item.toUpperCase()==Items.ORANGE.toString() }.toMutableList();
            return (appleList.subList(0,(appleList.size/2+appleList.size%2)).sumByDouble{ str-> getSingleItemCost(str)}) + (orrangeList.sumByDouble{ str-> getSingleItemCost(str)} * 2/3)
        }else{
            return itemList.sumByDouble{ str-> getSingleItemCost(str)}
        }


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