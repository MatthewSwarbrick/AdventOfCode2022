import arrow.core.Either
import arrow.core.left
import arrow.core.right

const val PART1_START_OF_PACKET_MARKER_SIZE = 4
const val PART2_START_OF_PACKET_MARKER_SIZE = 14

fun main() {
    part1()
    part2()
}

private fun part1() {
    input.getIndexOfFirstMarker(PART1_START_OF_PACKET_MARKER_SIZE)
        .map {
            println("Part 1 | $it")
        }
        .mapLeft {
            println(it)
        }
}

private fun part2() {
    input.getIndexOfFirstMarker(PART2_START_OF_PACKET_MARKER_SIZE)
        .map {
            println("Part 2 | $it")
        }
        .mapLeft {
            println(it)
        }
}

fun String.getIndexOfFirstMarker(markerSize: Int): Either<Error, Int> {
    this.foldIndexed(emptyList<Char>()) { index, acc, c ->
        if (acc.size < markerSize) {
            acc.plus(c)
        } else {
            if(acc.distinct().size == markerSize) {
                return index.right()
            } else {
                acc
                    .drop(1)
                    .take(markerSize - 1)
                    .plus(c)
            }
        }
    }

    return Error("Could not find marker").left()
}