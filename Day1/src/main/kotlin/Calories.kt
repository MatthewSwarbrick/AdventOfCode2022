import arrow.core.left
import arrow.core.right

@JvmInline
value class Calories private constructor(val value: Int) {
    companion object {
        operator fun invoke(value: Int) =
            if (value < 0) {
                Error("Invalid amount for calories").left()
            } else {
                Calories(value).right()
            }
    }
}