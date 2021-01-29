package config

import model.Item

object Inventory {

    var items = mutableListOf<Item>()

    init {
        items.forEach { item->
            println("Inventory---> ${item.name} count: ${item.count}" ) }
    }


}

class OrderInventory {

    open fun printInventory() {
        Inventory.items.forEach { item->
            println("Inventory---> ${item.name} count: ${item.count}" ) }
    }

    open fun getItemCount(name: String): Int {
       Inventory.items.forEach{item-> if(item.name == name){
            return item.count
        } }
        return 0
    }


    open fun reduceInventory(item:Item) {
        var localItem = Inventory.items.find { itm->  itm.name == item.name }
        var localItemCount = localItem?.count
        if (localItemCount != null) {
            localItemCount = localItemCount - item.count
            localItem?.count =localItemCount
        }
    }

    open fun addItem(item: Item) {
        Inventory.items.add(item)

    }

    open fun getItemCost(name: String): Double{
        Inventory.items.forEach{item-> if(item.name == name){
            return item.cost.toDouble()
        } }
        return 0f.toDouble()
    }

}