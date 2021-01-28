package service

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import services.OrdersService
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.test.assertEquals
@RunWith(JUnit4::class)
class OrderServiceTest {





@Test fun ordersCostTest(){
    var itemList: MutableList<String> = ArrayList<String>();
    var ordersService: OrdersService = OrdersService()
    itemList.add("apple")
    assertEquals(0.6f.toDouble(), ordersService.getTotalCost(itemList,false));
}

    @Test fun ordersCostTestWithOfferApple(){

        var itemList: MutableList<String> = ArrayList<String>();
        var ordersService: OrdersService = OrdersService()
        itemList.add("apple")
        itemList.add("apple")
        itemList.add("apple")
        assertEquals(1.2f.toDouble(), ordersService.getTotalCost(itemList,true));
    }

    @Test fun ordersCostTestWithOfferOrange(){

        var itemList: MutableList<String> = ArrayList<String>();
        var ordersService: OrdersService = OrdersService()
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        assertEquals(df.format(0.66f.toDouble()), df.format(ordersService.getTotalCost(itemList,true)));
    }

    @Test fun ordersCostTestWithOfferOrangeAndApple(){

        var itemList: MutableList<String> = ArrayList<String>();
        var ordersService: OrdersService = OrdersService()
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("apple")
        itemList.add("apple")
        itemList.add("apple")
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        assertEquals(df.format(0.66f.toDouble() + 1.2f.toDouble()), df.format(ordersService.getTotalCost(itemList,true)));
    }

    @Test fun ordersServicePublishEventsTest(){
        var ordersService: OrdersService = OrdersService()
        ordersService.publishOrderStatuses()
    }

    @Test fun checkInventoryUnavailableTest(){
        var ordersService: OrdersService = OrdersService()
        var itemList: MutableList<String> = ArrayList<String>();
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        itemList.add("orange")
        assertEquals(false,ordersService.checkInventory(itemList))
    }

    @Test fun checkInventoryAvailableTest(){
        var ordersService: OrdersService = OrdersService()
        var itemList: MutableList<String> = ArrayList<String>();
        itemList.add("orange")
        itemList.add("orange")
        assertEquals(true,ordersService.checkInventory(itemList))
    }

}