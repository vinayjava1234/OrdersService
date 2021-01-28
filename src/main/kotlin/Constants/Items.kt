package Constants

enum class Items(i :Float) {
    APPLE(0.60f),
    ORANGE(0.25f);
    val cost = i;

    open fun getCostItem(): Float {
       return cost;
    }

}