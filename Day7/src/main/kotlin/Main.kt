import Name.Companion.toName
import Size.Companion.toSize

private val TOTAL_DISK_SIZE = 70000000L.toSize()
private val REQUIRED_FREE_SPACE = 30000000L.toSize()

fun main() {
    val directories = getDirectories(input)
    part1(directories)
    part2(directories)
}

private fun part1(directories: Map<String, Directory>) {
    val sizes = directories.mapNotNull { (directoryPath, _) ->
        val size = directories.filterKeys { it.startsWith(directoryPath) }.values.sumOf { it.size().value }
    if(size <= 100_000) {
        size
    } else {
        null
    }
}
    println("Part 1 | ${sizes.sum()}")
}

private fun part2(directories: Map<String, Directory>) {
    val sizes = directories.map { (directoryPath, _) ->
        directories.filterKeys { it.startsWith(directoryPath) }.values.sumOf { it.size().value }
    }

    val usedSpace = sizes.max()
    val availableSpace = TOTAL_DISK_SIZE.value - usedSpace
    val requiredToDelete = REQUIRED_FREE_SPACE.value - availableSpace

    val smallestSize = sizes.filter { it >= requiredToDelete }.min()
    println("Part 2 | $smallestSize")
}

typealias Path = List<Name>

fun getDirectories(input: List<String>): Map<String, Directory> {
    var currentPath: Path = emptyList()
    return input
        .filter { !it.startsWith("dir") }
        .filter { !it.startsWith("$ ls") }
        .fold(emptyMap()) { acc, consoleOutput ->
        when {
            consoleOutput.startsWith("$") -> {
                val command = consoleOutput
                    .trimStart('$')
                    .trim()

                if(command.startsWith("cd")) {
                    val directoryName = command.split(" ").last().toName()
                    if(directoryName.value == "..") {
                        currentPath = currentPath.take(currentPath.size - 1)
                        acc
                    } else {
                        currentPath = currentPath.plus(directoryName)
                        if(!acc.containsKey(currentPath.toPathString())) {
                            acc.plus(currentPath.toPathString() to Directory(directoryName, emptySet()))
                        } else {
                            acc
                        }
                    }
                } else {
                    acc
                }
            }
            else -> {
                val file = File(consoleOutput)
                val updatedDirectory = acc[currentPath.toPathString()]?.addFile(file) ?: Directory(currentPath.last(), setOf(file))
                if(acc.containsKey(currentPath.toPathString())) {
                    acc.mapValues { (key, value) ->
                        if (key != currentPath.toPathString()) {
                            value
                        } else {
                            updatedDirectory
                        }
                    }
                } else {
                    acc.plus(currentPath.toPathString() to updatedDirectory)
                }
            }
        }
    }
}