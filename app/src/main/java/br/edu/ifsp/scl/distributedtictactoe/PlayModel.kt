package br.edu.ifsp.scl.distributedtictactoe

import kotlin.random.Random

data class PlayModel(
    var filledPos: MutableList<String> = mutableListOf("", "", "", "", "", "", "", "", ""),
    var winner: String = "",
    var roundPlayer: String = (arrayOf("X", "O"))[Random.nextInt(2)],
    var gameID: String = "-1",
    var currentStatus: Status = Status.C
)

enum class Status {
    W,  // Vitoria
    P,  // Jogando
    F,  // Fim
    C   // Criado -- Legendas p facilitar entendimento
}
