import java.util.Stack

data class CrateStack(private val crates: Stack<Crate>) {
    fun addToStack(crate: Crate) {
        crates.push(crate)
    }

    fun removeFromStack() = crates.pop()

    fun getTopCrate() = crates.peek()
}

@JvmInline
value class StackIndex(val value: Int)