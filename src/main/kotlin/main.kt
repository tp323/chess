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
    //remove comments in main to run UI
    //Window(onCloseRequest = ::exitApplication) {
        //App()
        inicialBoard()
        printBoardSmall()
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



