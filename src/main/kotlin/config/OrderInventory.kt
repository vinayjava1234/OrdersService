package config

object Inventory {

    var min = 3
    var max = 10
    var apples = min + (Math.random() * ((max - min) + 1)).toInt()
    var oranges = min + (Math.random() * ((max - min) + 1)).toInt()

    init {
        println("Inventory---> apple count: $apples orange count:$oranges")
    }


}

class OrderInventory {

    open fun printInventory(){
        println("Inventory---> apple count: ${Inventory.apples} orange count:${Inventory.oranges}")
    }

    open fun getAppleCount(): Int {
        return Inventory.apples
    }

    open fun getOrangeCount(): Int {
        return Inventory.oranges
    }

    open fun reduceInventory(apple: Int, orange: Int){
        Inventory.apples = Inventory.apples - apple
        Inventory.oranges = Inventory.oranges - orange
    }

}