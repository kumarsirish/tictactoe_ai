package com.netradevices.tictactoe

import java.security.KeyStore
import java.util.*

class tictactoe  {


    val matrixString = Array(3, { arrayOf(' ',' ',' ') })
    var matrixSize = 0

    constructor(size: Int) {

        for (i in 0..size - 1) {
            for (j in 0..size - 1) {
                matrixString[i][j] = '_'
            }
            matrixSize = size
        }
    }
    fun displayMatrix(){

        for ( i in 0..matrixSize-1) {
            for (j in 0..matrixSize-1) {
                print("${matrixString[i][j]} ")
            }
            println()
        }
        println()
    }

    fun updateCell(row : Int, col : Int, value: Char, player1Choice: Char, player2Choice: Char): Boolean {
       // matrix[row][col] = value
        var status = false
        if ((matrixString[row][col] != player1Choice) && (matrixString[row][col] != player2Choice)) {
                matrixString[row][col] = value
            status = true
            }

        return status
    }
}




data class player (val choice : Char =' '){

}

val player1 =1
val player2 = 2

fun main (args: Array<String>) {

    val boardSize = 3
    var board = tictactoe(boardSize)
    val player_1 = player('0')
    val player_2 = player('X')
    var choice : String
    val read = Scanner(System.`in`)
    var Turn = player1
    var winStatus = 0
    var updateStatus = 0
    var i = 0

    board.displayMatrix()

    while (i <= boardSize*boardSize-1) {
        println ("Step : $i")
        if ( whoseTurn(Turn) == player1) {
            print("Player 1: Enter your choice '${player_1.choice}' :  X(1-3) Y(1-3):")
        }
        else
            print("Player 2: Enter your choice '${player_2.choice}' X(1-3) Y(1-3):")

        val x = read.nextInt()
        val y = read.nextInt()

        if ( (x > 3 || x < 1) || ( y >3 || y < 1)) {
            println("Invalid Choice, Try again")
            i--
            continue
        }
        if ( whoseTurn(Turn) == player1) {
            if ( board.updateCell(x-1,y-1,player_1.choice,player_1.choice,player_2.choice) == false )
            {
                println("Cannot write at $x and $y. Try again")
                i--

            }
        } else
            if ( board.updateCell(x-1,y-1,player_2.choice, player_1.choice, player_2.choice) == false )
            {
                println("Cannot write at $x and $y. Try again")
                i--

            }

        board.displayMatrix()
        winStatus = checkWin(board.matrixString, player_1.choice, player_2.choice)
        if ( winStatus == player1 )
        {
            println("Player 1 is winner")
            break
        } else if (winStatus == player2){
            println("Player 2 is winner")
            break
        }

        Turn = switchTurn(Turn)
        i++
    }

    if ( i == boardSize * boardSize -1) {
        println("No Winner. Game Drawn")
    }

}

fun switchTurn (currentTurn : Int) : Int {
    var cTurn = currentTurn
    if ( cTurn == player1) {
        cTurn = player2
    } else
        cTurn = player1

    return cTurn
}

fun whoseTurn (currentTurn: Int) : Int {

    if ( currentTurn == player1)
           return player1
    else
           return player2


}

fun checkWin (matrix : Array<Array<Char>>, player1Choice : Char, player2Choice : Char) : Int{

    var winner = 0
    var winnerFound = false

    for (row in 0..2) {
        if ((matrix[row][0] == matrix[row][1]) && (matrix[row][1] == matrix[row][2])) {
            if (matrix[row][0] == player1Choice) {
                winner = player1
            } else if (matrix[row][0] == player2Choice)
                winner = player2

            winnerFound = true
            break
        }
    }
    if (!winnerFound) {
        for (col in 0..2) {
            if ((matrix[0][col] == matrix[1][col]) && (matrix[0][col] == matrix[2][col])) {
                if (matrix[0][col] == player1Choice) {
                    winner = player1
                } else if (matrix[0][col] == player2Choice)
                    winner = player2

                winnerFound = true
                break
            }
        }
    }

    if (!winnerFound) {
        if ( (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2])) {
            if (matrix[0][0] == player1Choice) {
                winner = player1
            } else if (matrix[0][0] == player2Choice)
                winner = player2

            winnerFound = true

        }
    }
    if (!winnerFound) {
        if ( (matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0])) {
            if (matrix[0][0] == player1Choice) {
                winner = player1
            } else if (matrix[0][0] == player2Choice)
                winner = player2

            winnerFound = true

        }
    }

    return winner
}