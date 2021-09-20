import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import java.util.Scanner

private val FIRSTCOLOR = Color.LightGray
private val SECONDCOLOR = Color.DarkGray

// 'a' -> king
// CAPS -> WHITE
private var BOARD = Array(8) { CharArray(8) }

fun inicialBoard() {
    BOARD[7] = charArrayOf('r','k','b','q','a','b','k','r')
    BOARD[6] = charArrayOf('p','p','p','p','p','p','p','p')

    for(n in 2..5) BOARD[n] = charArrayOf(' ',' ',' ',' ',' ',' ',' ',' ')

    BOARD[1] = charArrayOf('P','P','P','P','P','P','P','P')
    BOARD[0] = charArrayOf('R','K','B','Q','A','B','K','R')
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
    Window(onCloseRequest = ::exitApplication) {
        inicialBoard()
        board()
        printBoardSmall()
        //round()
        //printBoardSmall()
    }
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
                        Spacer(Modifier.size(65.dp).background(colorSquare)
                            .clickable(onClick = { })
                        )
                        if (BOARD[n-1][i-1] != ' ') {
                            Image(
                                painter = painterResource("Chess_rdt60.png"),
                                contentDescription = "image"//,
                                //contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                    squarepair =!squarepair
                }
                squarepair =!squarepair
            }
        }
/*
        val imageModifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))

        Image(bitmap = useResource("image.png") { loadImageBitmap(it) },
            "image",
            imageModifier,
            contentScale = ContentScale.Fit)*/


        /*val imageModifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))

        Image(painter = painterResource("image.png"),
            contentDescription = "image",
            imageModifier,
            contentScale = ContentScale.Fit
        )*/

    }
}

fun selectPiece(){

}

fun checkPiece(n: Int, l: Char): Char{
    val y = Character.getNumericValue(l+1)-Character.getNumericValue('a')
    val d = BOARD[n-1][y]
    return BOARD[n-1][y]
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
    //TODO: CHECK IF PIECE THERE AND TO WHICH POSITIONS IT CAN MOVE
    //check if piece from current player

    val nextPos = "" + position.subSequence(3, 5)

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

fun isChar(c: Char): Boolean{
    return c in 'A'..'Z' || c in 'a'..'z'
}

fun isInt(n: Char): Boolean{
    return n in '0'..'9'
}

fun isEmpty(pos: String): Boolean {
    val n = (pos[1]) - '0'
    return checkPiece(n,pos[0]) == ' '
}

fun isUpperCase(c: Char): Boolean{
    return c in 'A'..'Z'
}

fun convertToLowerCase(char: Char): Char {
    return char + 32
}