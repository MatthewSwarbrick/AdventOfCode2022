fun main() {
    val moves = getRopeMoves(input)
    part1(moves, 2)
    part2(moves, 10)
}

private fun part1(moves: List<Move>, numberOfKnots: Int) {
    val startPosition = Position(0, 0)
    val startRope = Rope((1..numberOfKnots).map { Knot(startPosition) })
    val ropes = moves.fold(listOf(startRope)) { acc, move ->
        val previousRope = acc.last()
        val newRopes = previousRope.move(move)
        acc.plus(newRopes)
    }

    val tailPositions = ropes.map { it.knots.last().position }.toSet()
    println("Part 1 | ${tailPositions.count()}")
}

private fun part2(moves: List<Move>, numberOfKnots: Int) {
    val startPosition = Position(0, 0)
    val startRope = Rope((1..numberOfKnots).map { Knot(startPosition) })
    val ropes = moves.fold(listOf(startRope)) { acc, move ->
        val previousRope = acc.last()
        val newRopes = previousRope.move(move)
        acc.plus(newRopes)
    }

    val tailPositions = ropes.map { it.knots.last().position }.toSet()
    println("Part 2 | ${tailPositions.count()}")
}

fun getRopeMoves(input: List<String>): List<Move> =
    input.map {
        val (direction, moveAmount) = it.split(" ")
        Move(direction.toDirection(), moveAmount.toInt())
    }

fun String.toDirection() =
    when(this) {
        "R" -> Direction.RIGHT
        "L" -> Direction.LEFT
        "U" -> Direction.UP
        "D" -> Direction.DOWN
        else -> throw Error("Unknown direction $this")
    }