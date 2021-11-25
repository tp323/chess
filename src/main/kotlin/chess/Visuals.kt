package chess

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FIRST_COLOR = Color.LightGray
private val SECOND_COLOR = Color.DarkGray
private val PIECES = arrayOf("pawn","rook","knight","bishop","queen","king")
private val TEAM = arrayOf("white","black")
private val SIZE_TILE = 65.dp
private val FONT_SIZE_BOARD = 30.sp
val moves: MutableList<String> = mutableListOf<String>()


//@OptIn(ExperimentalComposeUiApi::class)
@OptIn(ExperimentalComposeUiApi::class)
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
            val requester = remember { FocusRequester() }

            LaunchedEffect(Unit) {
                requester.requestFocus()
            }

            Text("   Play", textAlign = TextAlign.Center, fontSize = 30.sp)
            val move = remember { mutableStateOf("Play") }
            TextField(
                value = move.value,
                onValueChange = { move.value = it },
                label = { Text("Move") },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(20.dp)
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key != Key.Enter) return@onKeyEvent false
                        //if (keyEvent.type == KeyEventType.KeyUp) println(move.value)
                        if (keyEvent.key==Key.Enter && move.value!="") {
                            //if(algebraicNotationCheck(move.value)) {
                                println(move.value)
                                moves.add(move.value)
                            //}else
                            move.value = ""
                        }
                        true
                    }
                    .focusRequester(requester)
                    .focusable()
            )
            //Box{
                moves.forEach{ Text(it, modifier = Modifier.padding(20.dp)) }
            //}
        }
    }
}

@Composable
fun scrollList(products: List<String>) {

}

@Composable
fun board(n: Int, i: Int){
    var team = ""
    if(isWhite(BOARD[n-1][i-1])) team = TEAM[0]
    if(isBlack(BOARD[n-1][i-1])) team = TEAM[1]

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
