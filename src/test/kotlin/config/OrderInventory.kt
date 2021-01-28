package config

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class OrderInventoryTest {

    val inventory = OrderInventory()

    @Test
    fun getReduceInventoryTest() {
        val initialAppleCount = inventory.getAppleCount()
        val initialOrnageCount = inventory.getOrangeCount()
        inventory.reduceInventory(1, 2)
        assertEquals(initialAppleCount - 1, inventory.getAppleCount())
        assertEquals(initialOrnageCount - 2, inventory.getOrangeCount())
    }
}