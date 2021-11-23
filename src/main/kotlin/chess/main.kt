package chess


import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.concurrent.thread

// 'a' -> king  CAPS -> WHITE
val LOWER_CASE_LETTERS = charArrayOf('p','r','k','b','q','a')
val UPPER_CASE_LETTERS = charArrayOf('P','R','K','B','Q','A')



var BOARD = Array(8) { CharArray(8) }


//chess.round pair -> white chess.round not pair -> black
var checkMate = false
var player = true

fun resetBoard() {
    BOARD[0] = charArrayOf('R','K','B','Q','A','B','K','R')
    BOARD[1] = charArrayOf('P','P','P','P','P','P','P','P')
    BOARD[6] = charArrayOf('p','p','p','p','p','p','p','p')
    BOARD[7] = charArrayOf('r','k','b','q','a','b','k','r')
    for(n in 2..5) BOARD[n] = charArrayOf(' ',' ',' ',' ',' ',' ',' ',' ')
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
    resetBoard()
    printBoardSmall()
    Window(onCloseRequest = ::exitApplication, icon = painterResource("black_knight.png"), title = "Chess") {
        ui()
    }
    thread { game() }
    //thread { test() }

}


fun selectPiece(){

}

fun game(){
    println("Use Algebraic Notation")
    println("Ex: a2-b3")
    println("Whites turn")
    while(!checkMate){ round() }
}

fun round(){
    val currentPlayer = if(player) "White" else "Black"

    if(move(getPosition())){
        player = !player
        println(currentPlayer + "s turn")
        printBoardSmall()
    }
}

fun move(pastPos: String, nextPos: String) {
    val piece = coordinates(pastPos)
    BOARD[coordinateX(pastPos)][coordinateY(pastPos)] = ' '
    BOARD[coordinateX(nextPos)][coordinateY(nextPos)] = piece
}

fun coordinateY(pos: String): Int{ return Character.getNumericValue(pos[0])-Character.getNumericValue('a') }

fun coordinateX(pos: String): Int{ return (pos[1]) - '1'}

fun coordinates(pos: String): Char{ return BOARD[coordinateX(pos)][coordinateY(pos)] }

fun getPosition(): String{
    var position: String
    do{
        position = readLine()!!
        val t = !algebraicNotationCheck(position)
    }while(t)
    //working for Algebraic Notation only (for now)
    return position
}


fun checkPiece(n: Int,l: Char): Char{
    return BOARD[n-1][Character.getNumericValue(l+1)-Character.getNumericValue('a')]
}

fun createGamePieces(){
    //TODO: define each piece here to be able to track first chess.move on pawns
    /*val pw1 = GamePiece(true,'p',0,1)
    val pw2 = GamePiece(true,'p',1,1)
    val pw3 = GamePiece(true,'p',2,1)
    val pw4 = GamePiece(true,'p',3,1)
    val pw5 = GamePiece(true,'p',4,1)
    val pw6 = GamePiece(true,'p',5,1)
    val pw7 = GamePiece(true,'p',6,1)
    val pw8 = GamePiece(true,'p',7,1)
    val rw1 = GamePiece(true,'r',0,0)
    val rw2 = GamePiece(true,'r',7,0)
    val kw1 = GamePiece(true,'k',1,0)
    val kw2 = GamePiece(true,'k',6,0)
    val bw1 = GamePiece(true,'b',2,0)
    val bw2 = GamePiece(true,'b',5,0)
    val qw = GamePiece(true,'q',3,0)
    val aw = GamePiece(true,'a',4,0)

    val pb1 = GamePiece(false,'p',0,6)
    val pb2 = GamePiece(false,'p',1,6)
    val pb3 = GamePiece(false,'p',2,6)
    val pb4 = GamePiece(false,'p',3,6)
    val pb5 = GamePiece(false,'p',4,6)
    val pb6 = GamePiece(false,'p',5,6)
    val pb7 = GamePiece(false,'p',6,6)
    val pb8 = GamePiece(false,'p',7,6)
    val rb1 = GamePiece(false,'r',0,7)
    val rb2 = GamePiece(false,'r',7,7)
    val kb1 = GamePiece(false,'k',1,7)
    val kb2 = GamePiece(false,'k',6,7)
    val bb1 = GamePiece(false,'b',2,7)
    val bb2 = GamePiece(false,'b',5,7)
    val qb = GamePiece(false,'q',3,7)
    val ab = GamePiece(false,'a',4,7)*/

}