import chess.algebraicNotationCheck
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NotationsTest {
    @Test
    fun algebraicEmpty(){
        assertEquals(false, algebraicNotationCheck(""))
    }
    @Test
    fun algebraicSwitchedCharsWithNumbers(){
        assertEquals(false, algebraicNotationCheck("2b-2b"))
    }
    @Test
    fun algebraicOutsideBoardRow(){
        assertEquals(false, algebraicNotationCheck("a9-b2"))
    }
    @Test
    fun algebraicOutsideBoardCol(){
        assertEquals(false, algebraicNotationCheck("i2-b2"))
    }
    @Test
    fun algebraicEx1(){
        assertEquals(true, algebraicNotationCheck("a2-b2"))
    }
    @Test
    fun algebraicEx2(){
        assertEquals(true, algebraicNotationCheck("a8-b6"))
    }
    @Test
    fun algebraicEx3(){
        assertEquals(true, algebraicNotationCheck("h2-e3"))
    }
}