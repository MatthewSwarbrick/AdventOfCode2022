data class Rucksack(val compartment1: List<Item>, val compartment2: List<Item>) {
    fun allItems() =
        compartment1.distinct().plus(compartment2.distinct())

    fun findDuplicate() =
        allItems()
            .duplicates().firstOrNull()
}