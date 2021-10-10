import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

private val FIRST_COLOR = Color.LightGray
private val SECOND_COLOR = Color.DarkGray

// 'a' -> king  CAPS -> WHITE
private val LOWER_CASE_LETTERS = charArrayOf('p','r','k','b','q','a')
private val UPPER_CASE_LETTERS = charArrayOf('P','R','K','B','Q','A')

private val PIECES = arrayOf("pawn","rook","knight","bishop","queen","king")
private val TEAM = arrayOf("white","black")

private val SIZE_TILE = 65.dp
private val FONT_SIZE_BOARD = 30.sp

var BOARD = Array(8) { CharArray(8) }

val utils = Utils()
val notations = Notations()

//round pair -> white round not pair -> black
var checkMate = false
var player = true

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
    resetBoard()
    printBoardSmall()
    Window(onCloseRequest = ::exitApplication, icon = painterResource("black_knight.png"), title = "Chess") {
        ui()
        game()
    }
}

@Composable
fun ui() {
    var squarePair = false
    Row {
        Column {
            for (n in 8 downTo 1) {
                Row {
                    Text(
                        "" + n,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(SIZE_TILE),
                        fontSize = FONT_SIZE_BOARD,
                        fontWeight = FontWeight.Bold
                    )
                    squarePair = boardLines(n, squarePair)
                }
            }
            Row {
                Text(" ", textAlign = TextAlign.Center, modifier = Modifier.width(SIZE_TILE))
                for (n in 0..7) {
                    Text(
                        "" + ('A' + n),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(SIZE_TILE),
                        fontSize = FONT_SIZE_BOARD,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Column {
            Text("   Play", textAlign = TextAlign.Center, fontSize = 30.sp)
            var move = ""
            //var move by remember { mutableStateOf("") }
            TextField(
                value = move,
                onValueChange = { move = it },
                label = { Text("Move") },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(20.dp)
            )
            print(move)
        }
    }
}

@Composable
fun board(n: Int, i: Int){
    var team = ""
    if(utils.isUpperCase(BOARD[n-1][i-1])) team = TEAM[0]
    if(utils.isLowerCase(BOARD[n-1][i-1])) team = TEAM[1]

    for(k in LOWER_CASE_LETTERS.indices) {
        if (BOARD[n-1][i-1] == LOWER_CASE_LETTERS[k] || BOARD[n-1][i-1] == UPPER_CASE_LETTERS[k]) {
            Image(painter = painterResource(team + "_" + PIECES[k] + ".png"), contentDescription = PIECES[k])
        }
    }
}

@Composable
fun boardLines(n: Int, squarePair: Boolean): Boolean{
    var colorSquare: Color
    var pair = squarePair

    for (i in 1..8) {
        colorSquare = if (!pair) FIRST_COLOR
        else SECOND_COLOR
        Box {
            Spacer(Modifier.size(SIZE_TILE).background(colorSquare).clickable(onClick = { }))
            if (BOARD[n-1][i-1] != ' ') board(n, i)
        }
        pair = !pair
    }
    return !pair
}

fun selectPiece(){

}

fun game(){ while(!checkMate){ round() } }

fun round(){
    if(notations.algebraicNotation(getPosition())){
        player = !player
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
        val t = position.length!=5 || !algebraicNotationCheck(position)
    }while(t)
    //working for Algebraic Notation only (for now)
    return position
}

fun algebraicNotationCheck(position: String): Boolean{
    val pastPos = utils.isChar(position[0]) && utils.isInt(position[1])
    val nextPos = utils.isChar(position[3]) && utils.isInt(position[4])
    val bol = pastPos && nextPos
    if (!bol){
        println("Isn't in Algebraic Notation")
        return false
    }
    return true
}

fun checkPiece(n: Int,l: Char): Char{
    return BOARD[n-1][Character.getNumericValue(l+1)-Character.getNumericValue('a')]
}