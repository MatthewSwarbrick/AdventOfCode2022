import Name.Companion.toName

fun main() {
    val directories = getDirectories(input)
    println(
        directories.keys.joinToString("\n") { it }
    )
    part1(directories)
    part2()
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

private fun part2() {
    println("Part 2 | ")
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