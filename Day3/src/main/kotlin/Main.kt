
fun main() {
    val rucksacks = getRucksacks(input)
    part1(rucksacks)
    part2(rucksacks)
}

private fun part1(rucksacks: List<Rucksack>) {
    val totalPriority = rucksacks.mapNotNull {
        it.findDuplicate()
    }.sumOf { it.priority }

    println("Part 1 | $totalPriority")
}

private fun part2(rucksacks: List<Rucksack>) {
    val totalPriority = rucksacks.toElfGroups()
        .sumOf { it.badge().priority }

    println("Part 2 | $totalPriority")
}

private fun getRucksacks(input: List<String>) =
    input
        .map { it.halved() }
        .map {
            it.map { letters ->
                letters.map { letter -> Item(letter) }
            }
        }
        .map { (compartment1, compartment2) ->
            Rucksack(compartment1, compartment2)
        }

private fun String.halved() =
    this.chunked(this.length / 2)
