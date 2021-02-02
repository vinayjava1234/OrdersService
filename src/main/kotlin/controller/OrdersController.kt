package com.example.demo.controller

import Constants.Items
import com.example.demo.utils.OrderUtils

import config.OrderInventory
import model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import services.OrdersService
import java.util.concurrent.atomic.AtomicLong


@RestController
class OrdersController {


    @PostMapping(path= arrayOf("/submitOrder"), consumes = arrayOf("application/json"), produces = arrayOf("application/json"))
    fun submitOrder(@RequestBody orders: List<Order>,@RequestParam(value = "offer", defaultValue = "No")offer: String){

        val ordersService = OrdersService()
        ordersService.checkInventoryAndProcessOrder(
            OrderUtils.getItemList(orders),
            (offer.toUpperCase()=="YES")
        )
    }
}