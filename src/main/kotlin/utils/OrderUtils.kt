package com.example.demo.utils

import Constants.Items
import config.OrderInventory
import model.Item
import model.Order
import services.OrdersService

class OrderUtils {

    companion object {

        fun getItemList(orders: List<Order>): MutableList<String>{

            var itemList: MutableList<String> = ArrayList<String>()
            orders.forEach { order->
                var i = order.count
                while (i!=0){
                    itemList.add(order.name)
                    i--
                }
            }
            val orderInventory = OrderInventory()
            val apple = Item(Items.APPLE.toString(), 0.6f.toDouble(), (1 + (Math.random() * 5).toInt()))
            val orange = Item(Items.ORANGE.toString(), 0.25f.toDouble(), (1 + (Math.random() * 5).toInt()))
            orderInventory.addItem(apple)
            orderInventory.addItem(orange)
            return itemList
        }

    }

}