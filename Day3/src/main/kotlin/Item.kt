@JvmInline
value class Item private constructor(val priority: Int) {
    companion object {
        operator fun invoke(letter: Char) =
            when(letter.isLowerCase()) {
                true -> Item(priority = letter.code - 96)
                false -> Item(priority = letter.code - 38)
            }
    }
}

fun List<Item>.duplicates() =
    this.groupingBy { it }.eachCount().filter { it.value > 1 }.keys