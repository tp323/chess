class GamePiece {
    var team = true
    var piece = ' '
    var pastPosX = -1
    var pastPosY = -1


    fun piece() {
        when (piece) {
            'p' -> print("pawn")
            'r' -> print("rook")
            'k' -> print("knight")
            'b' -> print("bishop")
            'q' -> print("queen")
            'a' -> print("king")
            else -> print("no valid piece")
        }
    }

    fun possibleMoves(x: Int, y: Int): Boolean {
        return when (piece) {
            'p' -> movePawn(x, y)
            'r' -> moveRook(x, y)
            'k' -> moveKnight(x, y)
            'b' -> moveBishop(x, y)
            'q' -> moveQueen(x, y)
            'a' -> moveKing(x, y)
            else -> false
        }
    }

    private fun movePawn(x: Int, y: Int): Boolean {
        if (!insideBoard(x,y)) return false
        //Normal Move
        if (x == pastPosX + 1 && y == pastPosY && BOARD[x][y] == ' ') return true
        //Capture
        if (x == pastPosX + 1 && (y == pastPosY + 1 || y == pastPosY - 1) && team != utils.isWhite(BOARD[x][y])) return true
        return false
    }

    private fun moveRook(x: Int, y: Int): Boolean {
        return false
    }

    private fun moveKnight(x: Int, y: Int): Boolean {
        return false
    }

    private fun moveBishop(x: Int, y: Int): Boolean {
        if (x == pastPosX + 1 && y == pastPosY) return true
        return false
    }

    private fun moveQueen(x: Int, y: Int): Boolean {
        return false
    }

    private fun moveKing(x: Int, y: Int): Boolean {
        return false
    }

    private fun insideBoard(x: Int, y: Int): Boolean { return x in 0..7 && y in 0..7 }
}