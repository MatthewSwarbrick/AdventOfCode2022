data class Round(val player1: Hand, val player2: Hand) {
    fun result() = when(player1) {
        Hand.ROCK -> when(player2) {
            Hand.ROCK -> RoundResult(Result.DRAW, Result.DRAW)
            Hand.PAPER -> RoundResult(Result.LOSS, Result.WIN)
            Hand.SCISSORS -> RoundResult(Result.WIN, Result.LOSS)
        }
        Hand.PAPER -> when(player2) {
            Hand.ROCK -> RoundResult(Result.WIN, Result.LOSS)
            Hand.PAPER -> RoundResult(Result.DRAW, Result.DRAW)
            Hand.SCISSORS -> RoundResult(Result.LOSS, Result.WIN)
        }
        Hand.SCISSORS -> when(player2) {
            Hand.ROCK -> RoundResult(Result.LOSS, Result.WIN)
            Hand.PAPER -> RoundResult(Result.WIN, Result.LOSS)
            Hand.SCISSORS -> RoundResult(Result.DRAW, Result.DRAW)
        }
    }
}

enum class Result {
    WIN,
    DRAW,
    LOSS
}

fun Result.score() = when(this) {
    Result.WIN -> 6
    Result.DRAW -> 3
    Result.LOSS -> 0
}

data class RoundResult(val player1: Result, val player2: Result)