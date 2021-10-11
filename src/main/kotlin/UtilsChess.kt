class UtilsChess {

    fun isEmpty(pos: String): Boolean {
        val n = (pos[1]) - '0'
        return checkPiece(n,pos[0]) == ' '
    }

    fun isInt(n: Char): Boolean{ return n in '0'..'9' }
    fun isChar(c: Char): Boolean{ return c in 'A'..'Z' || c in 'a'..'z' }

    fun isBlack(c: Char): Boolean{ return c in 'a'..'z' }
    fun isWhite(c: Char): Boolean{ return c in 'A'..'Z' }

    fun convertToLowerCase(char: Char): Char { return char + 32 }
    fun convertToUpperCase(char: Char): Char { return char - 32 }
}