package config

import Constants.Items
import model.Item
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class OrderInventoryTest {

    val inventory = OrderInventory()

    @Test
    fun getReduceInventoryTest() {
        val initialapple = Item(Items.APPLE.toString(),0.6f.toDouble(),10)
        val initialorange = Item(Items.ORANGE.toString(),0.25f.toDouble(),10)
        inventory.addItem(initialapple)
        inventory.addItem(initialorange)
        var reduceInventoryItem = Item(Items.APPLE.toString(), 0f.toDouble(), 2)
        var reduceInventoryItem2 = Item(Items.ORANGE.toString(), 0f.toDouble(), 1)
        inventory.reduceInventory(reduceInventoryItem)
        inventory.reduceInventory(reduceInventoryItem2)
        assertEquals(10 - 2, inventory.getItemCount(Items.APPLE.toString()))
        assertEquals(10 - 1, inventory.getItemCount(Items.ORANGE.toString()))
    }
}