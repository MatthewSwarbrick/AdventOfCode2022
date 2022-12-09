import kotlin.math.abs

data class Rope(val knots: List<Knot>) {
    fun move(move: Move): List<Rope> =
        (1..move.amount).fold(listOf(this)) { acc, _ ->
            val prevRope = acc.last()
            val newRope = prevRope.moveOnce(move.direction)
            acc.plus(newRope)
        }

    override fun toString(): String =
        (-5..5).map { y ->
            (-5..5).map { x ->
                when (val knotIndex = this.knots.indexOfFirst { it.position.x == x && it.position.y == y }) {
                    0 -> "H"
                    -1 -> "."
                    else -> "$knotIndex"
                }
            }.joinToString(" ") { it }
        }.joinToString("\n") { it }

    private fun moveOnce(direction: Direction): Rope {
        val newKnots = this.knots.fold(emptyList<Knot>()) { acc, knot ->
            val parent = acc.lastOrNull()
            if (parent == null) {
                acc.plus(this.knots.first().toNewPosition(direction))
            } else {
                val newTail = if (knot.isTouching(parent)) {
                    knot
                } else {
                    knot.toNewPosition(parent)
                }
                acc.plus(newTail)
            }
        }

        return Rope(newKnots)
    }

}

@JvmInline
value class Knot(val position: Position) {
    fun isTouching(head: Knot): Boolean =
        abs(this.position.x - head.position.x) <= 1 &&
                abs(this.position.y - head.position.y) <= 1

    fun toNewPosition(direction: Direction): Knot =
        when (direction) {
            Direction.UP -> Knot(Position(this.position.x, this.position.y - 1))
            Direction.DOWN -> Knot(Position(this.position.x, this.position.y + 1))
            Direction.LEFT -> Knot(Position(this.position.x - 1, this.position.y))
            Direction.RIGHT -> Knot(Position(this.position.x + 1, this.position.y))
        }

    fun toNewPosition(parent: Knot): Knot {
        val distanceInX = this.position.x - parent.position.x
        val distanceInY = this.position.y - parent.position.y

        return when {
            abs(distanceInY) > abs(distanceInX) -> {
                Knot(Position(parent.position.x, if (distanceInY > 0) this.position.y - 1 else this.position.y + 1))
            }

            abs(distanceInY) < abs(distanceInX) -> {
                Knot(Position(if (distanceInX > 0) this.position.x - 1 else this.position.x + 1, parent.position.y))
            }

            else ->
                Knot(
                    Position(
                        if (distanceInX > 0) this.position.x - 1 else this.position.x + 1,
                        if (distanceInY > 0) this.position.y - 1 else this.position.y + 1
                    )
                )

        }
    }
}


data class Position(val x: Int, val y: Int)

data class Move(val direction: Direction, val amount: Int)

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}