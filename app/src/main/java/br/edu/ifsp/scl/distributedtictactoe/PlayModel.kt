package br.edu.ifsp.scl.distributedtictactoe

import kotlin.random.Random

class PlayModel {
    var filledPos : MutableList<String> = mutableListOf("","", "", "", "", "", "", "", "")
    var winner : String = ""
    var roundPlayer : String = (arrayOf("X", "O"))[Random.nextInt(2)]
    var gameID : String = "-1"
}