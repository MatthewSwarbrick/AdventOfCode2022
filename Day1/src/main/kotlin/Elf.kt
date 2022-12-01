@JvmInline
value class Elf(val foodItems: List<FoodItem>) {
    fun totalCalories() = this.foodItems.sumOf { it.calories.value }
}