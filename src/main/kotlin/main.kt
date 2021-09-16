import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import java.util.Scanner

private val FIRSTCOLOR = Color.LightGray
private val SECONDCOLOR = Color.Black

// 'a' -> king
// CAPS -> WHITE
private var BOARD = Array(8) { CharArray(8) }

fun inicialBoard() {
    BOARD[0] = charArrayOf('r','k','b','q','a','b','k','r')
    BOARD[1] = charArrayOf('p','p','p','p','p','p','p','p')

    for(n in 2..5) BOARD[n] = charArrayOf(' ',' ',' ',' ',' ',' ',' ',' ')

    BOARD[6] = charArrayOf('P','P','P','P','P','P','P','P')
    BOARD[7] = charArrayOf('R','K','B','Q','A','B','K','R')
}

fun printBoard(){
    for(x in 0..7){
        println("   +---+---+---+---+---+---+---+---+")
        print("" + (8-x) + "  | ")
        for(y in 0..7){
            print(BOARD[x][y] + " | ")
        }
        println("")
    }
    println("   +---+---+---+---+---+---+---+---+")
    println("     a   b   c   d   e   f   g   h")
}

fun printBoardSmall(){
    for(x in 0..7){
        print("" + (8-x) + "  | ")
        for(y in 0..7){
            print(BOARD[x][y] + " | ")
        }
        println("")
    }
    println("     a   b   c   d   e   f   g   h")
}


fun main() = application {
    val input = Scanner(System.`in`)
    //remove comments in main to run UI
    //Window(onCloseRequest = ::exitApplication) {
        //App()
        inicialBoard()
        printBoardSmall()
        //round()
        //board()
    //}
}


@Composable
fun board() {
    var squarepair = false
    var colorSquare: Color

    Column {
        for(n in 1..8) {
            Row {
                for (i in 1..8) {
                    colorSquare = if (!squarepair) FIRSTCOLOR
                    else SECONDCOLOR
                    Box {
                        Spacer(Modifier.size(50.dp).background(colorSquare)
                                .clickable(onClick = { }))
                    }
                    squarepair =!squarepair
                }
                squarepair =!squarepair
            }
        }
    }
}

fun selectPiece(){

}

fun checkPiece(n: Int, l: Char): Char{
    val y = Character.getNumericValue(l+1)-Character.getNumericValue('a')
    return BOARD[n][y]
}

fun round(){
    val position = getPosition()

}

fun algebraicNotation(position: String){
    val pastPos = position.subSequence(0, 1)
    //TODO: CHECK IF PIECE THERE AND TO WICH POSITIONS IT CAN MOVE

    val nextPos = position.subSequence(3, 4)

}

fun getPosition(): String{
    var position = ""
    do{
        position = readLine()!!
    }while(position.length!=5 && isChar(position[0]) && isInt(position[1]) && isChar(position[3]) && isInt(position[4]))
    //working for Algebraic Notation only (for now)
    return position
}

fun isChar(c: Char): Boolean{
    return c in 'A'..'Z' || c in 'a'..'z'
}

fun isInt(n: Char): Boolean{
    return n in '0'..'9'
}

fun isEmpty(pos: String): Boolean {
    val n = (pos[1]).code - '0'.code
    return checkPiece(n,pos[0]) == ' '
}