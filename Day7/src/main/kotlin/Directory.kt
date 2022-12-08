import Size.Companion.toSize
import arrow.core.left
import arrow.core.right

data class Directory(val name: Name, val files: Set<File>) {
    fun size(): Size = files.sumOf { it.size.value }.toSize()

    fun addFile(file: File): Directory = Directory(this.name, this.files.plus(file))
}

@JvmInline
value class Name(val value: String) {
    companion object {
        fun String.toName() = Name(this)
    }
}

fun List<Name>.toPathString() = "C:/ ${this.drop(1).joinToString(" / ") { it.value }}"

data class File(val name: Name, val size: Size) {
    companion object {
        operator fun invoke(value: String): File {
            val (sizeString, nameString) = value
                .split(" ")

            return File(Name(nameString), sizeString.toLong().toSize())
        }
    }
}

@JvmInline
value class Size private constructor(val value: Long) {
    companion object {
        operator fun invoke(value: Long) =
            if(value < 0) {
                Error("Invalid size. Must be greater").left()
            } else {
                Size(value).right()
            }

        fun Long.toSize() = Size(this)
    }
}