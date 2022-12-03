@JvmInline
value class ElfGroup(private val rucksacks: List<Rucksack>) {
    fun badge(): Item {
        val (rucksack1, rucksack2, rucksack3) = rucksacks

        val items1 = rucksack1.allItems().distinct()
        val items2 = rucksack2.allItems().distinct()
        val items3 = rucksack3.allItems().distinct()

        val commonInItems1And2 = items1.plus(items2).duplicates().toList()
        return commonInItems1And2.plus(items3).duplicates().first()
    }
}

fun List<Rucksack>.toElfGroups() =
    this.chunked(3)
        .map {
            ElfGroup(it)
        }