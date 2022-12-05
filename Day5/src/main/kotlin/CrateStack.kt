import java.util.Stack

data class CrateStack(private val crates: Stack<Crate>) {
    fun addToStack(crate: Crate) {
        crates.push(crate)
    }

    fun removeFromStack(): Crate = crates.pop()

    fun getTopCrate(): Crate = crates.peek()
}

@JvmInline
value class StackIndex(val value: Int)