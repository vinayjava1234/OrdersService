package service

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import services.OrdersService
import kotlin.test.assertEquals
@RunWith(JUnit4::class)
class OrderServiceTest {




@Test fun ordersCostTest(){

    var itemList: MutableList<String> = ArrayList<String>();
    var ordersService: OrdersService = OrdersService()
    itemList.add("apple")
    assertEquals(0.6f.toDouble(), ordersService.getTotalCost(itemList));
}

}