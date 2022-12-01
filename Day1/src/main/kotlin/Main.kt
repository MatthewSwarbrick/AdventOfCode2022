import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.right

fun main() {
    getElvesFromInput(input)
        .map { elves ->
            part1(elves)
            part2(elves)
        }
}

private fun part1(elves: List<Elf>) {
    val highestCalories = elves.maxOf { it.totalCalories() }
    println("Part 1 | Highest calories: $highestCalories")
}

private fun part2(elves: List<Elf>) {
    val totalCalories = elves
        .sortedBy { it.totalCalories() }
        .takeLast(3)
        .sumOf { it.totalCalories() }

    println("Part 2 | Total calories of top three: $totalCalories")
}

private fun getElvesFromInput(input: List<String>) =
    input.fold<String, Either<Error, List<Elf>>>(emptyList<Elf>().right()) { acc, s ->
        either.eager {
            if (s.isEmpty()) {
                val elves = acc.bind()
                elves.plus(Elf(emptyList()))
            } else {
                val calories = Calories(s.toInt()).bind()
                val foodItem = FoodItem(calories)
                val elves = acc.bind()
                val updatedElf = Elf(
                    (elves.lastOrNull()?.foodItems ?: emptyList())
                        .plus(foodItem)
                )

                if(elves.isNotEmpty()) {
                    elves.take(elves.size - 1).plus(updatedElf)
                } else {
                    listOf(updatedElf)
                }
            }
        }
    }