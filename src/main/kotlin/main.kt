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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

private val FIRSTCOLOR = Color.LightGray
private val SECONDCOLOR = Color.Black

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    DesktopMaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        //App()
        //MatchParentSizeComposable()
        board()
    }
}

@Composable
fun grid(name: String){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End) {
        Column {
            Text("Hello")
            Text(name)
        }
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

