fun main() {
    val trees = getTrees(input)
    part1(trees)
    part2(trees)
}

private fun part1(trees: List<Tree>) {
    println("Part 1 | ${trees.count { trees.isVisible(it) }}")
}

private fun part2(trees: List<Tree>) {
    println("Part 2 | ${trees.maxOf { trees.scenicScore(it).value }}")
}

fun getTrees(input: List<String>) =
    input.flatMapIndexed { y, row ->
        row.mapIndexed { x, treeHeight ->
            Tree(Position(x, y), Height(treeHeight.digitToInt()))
        }
    }