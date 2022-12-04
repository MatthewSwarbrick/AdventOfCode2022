
fun main() {
    val pairs = getPairs(input)
    part1(pairs)
    part2(pairs)
}

private fun part1(pairs: List<Pair<Elf, Elf>>) {
    val numberOfOverlappingPairs = pairs.count { it.first.isOverlappingCompletelyWithElf(it.second) }
    println("Part 1 | $numberOfOverlappingPairs")
}

private fun part2(pairs: List<Pair<Elf, Elf>>) {
    val numberOfOverlappingPairs = pairs.count { it.first.isOverlappingPartiallyWithElf(it.second) }
    println("Part 2 | $numberOfOverlappingPairs")
}

private fun getPairs(input: List<String>) =
    input
        .map {
            val (elf1, elf2) = it.split(",")
                .map { elf ->
                    val (lowerBound, upperBound) = elf.split("-")
                    Elf(Bound(lowerBound.toInt()), Bound(upperBound.toInt()))
                }

            elf1 to elf2
        }
