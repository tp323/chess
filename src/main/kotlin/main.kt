import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import java.util.Scanner

private val FIRST_COLOR = Color.LightGray
private val SECOND_COLOR = Color.DarkGray

// 'a' -> king
// CAPS -> WHITE
private var BOARD = Array(8) { CharArray(8) }

private val LOWER_CASE_LETTERS = charArrayOf('p','r','k','b','q','a')
private val UPPER_CASE_LETTERS = charArrayOf('P','R','K','B','Q','A')

private val PIECES = arrayOf("pawn","rook","knight","bishop","queen","king")
private val TEAM = arrayOf("white","black")

fun resetBoard() {
    BOARD[0] = charArrayOf('R','K','B','Q','A','B','K','R')
    BOARD[1] = charArrayOf('P','P','P','P','P','P','P','P')
    BOARD[6] = charArrayOf('p','p','p','p','p','p','p','p')
    BOARD[7] = charArrayOf('r','k','b','q','a','b','k','r')
    for(n in 2..5) BOARD[n] = charArrayOf(' ',' ',' ',' ',' ',' ',' ',' ')
}

fun printBoard(){
    for(x in 0..7){
        println("   +---+---+---+---+---+---+---+---+")
        print("" + (8-x) + "  | ")
        for(y in 7 downTo 1){
            print(BOARD[x][y] + " | ")
        }
        println("")
    }
    println("   +---+---+---+---+---+---+---+---+")
    println("     a   b   c   d   e   f   g   h")
}

fun printBoardSmall(){
    for(x in 7 downTo 0){
        print("" + (1+x) + "  | ")
        for(y in 0..7) print(BOARD[x][y] + " | ")
        println("")
    }
    println("     a   b   c   d   e   f   g   h")
}

fun main() = application {

    val input = Scanner(System.`in`)
    Window(onCloseRequest = ::exitApplication) {
        resetBoard()
        ui()
        printBoardSmall()
        //round()
    }
}

@Composable
fun ui() {
    var squarePair = false
    var colorSquare: Color
    Column {
        for(n in 1..8) {
            Row {
                for (i in 1..8) {
                    colorSquare = if (!squarePair) FIRST_COLOR
                    else SECOND_COLOR
                    Box {
                        Spacer(Modifier.size(65.dp).background(colorSquare)
                            .clickable(onClick = { })
                        )
                        if(BOARD[n-1][i-1] != ' ') board(n,i)
                    }
                    squarePair =!squarePair
                }
                squarePair =!squarePair
            }
        }
    }
}

@Composable
fun board(n: Int, i: Int){
    var team = ""
    if(isLowerCase(BOARD[n-1][i-1])) team = TEAM[0]
    if(isUpperCase(BOARD[n-1][i-1])) team = TEAM[1]
    for(k in LOWER_CASE_LETTERS.indices) {
        if (BOARD[n - 1][i - 1] == LOWER_CASE_LETTERS[k] || BOARD[n - 1][i - 1] == UPPER_CASE_LETTERS[k]) {
            Image(
                painter = painterResource(team + "_" + PIECES[k] + ".png"),
                contentDescription = PIECES[k]
            )
        }
    }
}

fun selectPiece(){

}

fun round(){
    algebraicNotation(getPosition())

}

fun algebraicNotation(position: String){
    val pastPos = "" + position.subSequence(0, 2)
    if(isEmpty(pastPos)) {
        println("Can't Print")
        return
    }
    val nextPos = "" + position.subSequence(3, 5)

    //TODO: CHECK IF PIECE THERE AND TO WHICH POSITIONS IT CAN MOVE
    //check if piece from current player

    move(pastPos,nextPos)
}

fun move(pastPos: String, nextPos: String) {
    val yPastPos = Character.getNumericValue(pastPos[0])-Character.getNumericValue('a')
    val xPastPos = (pastPos[1]) - '1'

    val piece = BOARD[xPastPos][yPastPos]
    BOARD[xPastPos][yPastPos] = ' '

    val yNextPos = Character.getNumericValue(nextPos[0])-Character.getNumericValue('a')
    val xNextPos = (nextPos[1]) - '1'

    BOARD[xNextPos][yNextPos] = piece
}

fun checkValidPlay(piece: Char, pastPos: String, nextPos: String): Boolean{

    return false
}

fun getPosition(): String{
    var position = ""
    do{
        position = readLine()!!
    }while(position.length!=5 && isChar(position[0]) && isInt(position[1]) && isChar(position[3]) && isInt(position[4]))
    //working for Algebraic Notation only (for now)
    return position
}

fun checkPiece(n: Int,l: Char): Char{
    return BOARD[n-1][Character.getNumericValue(l+1)-Character.getNumericValue('a')]
}

fun isEmpty(pos: String): Boolean {
    val n = (pos[1]) - '0'
    return checkPiece(n,pos[0]) == ' '
}

fun isInt(n: Char): Boolean{ return n in '0'..'9' }
fun isChar(c: Char): Boolean{ return c in 'A'..'Z' || c in 'a'..'z' }

fun isLowerCase(c: Char): Boolean{ return c in 'a'..'z' }
fun isUpperCase(c: Char): Boolean{ return c in 'A'..'Z' }

fun convertToLowerCase(char: Char): Char { return char + 32 }
fun convertToUpperCase(char: Char): Char { return char - 32 }