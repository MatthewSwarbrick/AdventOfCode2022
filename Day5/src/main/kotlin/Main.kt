import java.util.*

fun main() {
    val crateStacks = getCrates(input)
    val instructions = getInstructions(input)
    part1(crateStacks, instructions)
    part2()
}

private fun part1(crateStacks: Map<StackIndex, CrateStack>, instructions: List<Instruction>) {
    instructions.map { instruction ->
        IntRange(1, instruction.amountToMove)
            .mapNotNull { crateStacks[instruction.fromStack]?.removeFromStack() }
            .map { crateStacks[instruction.toStack]?.addToStack(it) }
    }

    val topCrates = crateStacks.values
        .map { it.getTopCrate() }
        .joinToString (separator = "") { it.letter }

    println("Part 1 | $topCrates")
}

private fun part2() {
    println("Part 2 | ")
}

fun getCrates(input: List<String>) =
    input
        .takeWhile { !it.trim().startsWith("1") }
        .reversed()
        .flatMap { cratesInRow ->
            cratesInRow.chunked(4)
                .map { crateLetter ->
                    crateLetter
                        .trim()
                        .trimStart { it == '[' }
                        .trimEnd { it == ']' }
                }
                .mapIndexed { index, crateLetter ->
                    if(crateLetter.isBlank()) {
                        null
                    } else {
                        StackIndex(index + 1) to Crate(crateLetter)
                    }
                }
                .filterNotNull()
        }
        .fold(emptyMap<StackIndex, CrateStack>()) { acc, (stackIndex, crate) ->
            val crateStack = acc[stackIndex]
            if(crateStack == null) {
                acc.plus(stackIndex to CrateStack(Stack()).apply { this.addToStack(crate) })
            } else {
                crateStack.addToStack(crate)
                acc
            }
        }

fun getInstructions(input: List<String>) =
    input
        .reversed()
        .takeWhile { it.trim().isNotBlank() }
        .reversed()
        .map {
            val (amountToMove, from, to) = it.split(" ")
                .filterNot { part -> part == "move" || part == "from" || part == "to" }
                .map { part -> part.toInt() }

            Instruction(amountToMove, StackIndex(from), StackIndex(to))
        }