class GamePiece(
    //team = true is WHITE team = false is BLACK
    var team: Boolean, var piece: Char, var pastPosX: Int, var pastPosY: Int) {

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
        if (team && x == pastPosX + 1 && y == pastPosY && BOARD[x][y] == ' ') return true
        if (!team && x == pastPosX - 1 && y == pastPosY && BOARD[x][y] == ' ') return true

        //first move 2 cells
        //TODO: Move 2 cells in the case that it's the first move (need to track if piece has already move)
        //if piece is still on the line were its team pawns start it still hasn't moved for WHITE line 2 and for BLACK line 7
        var teamStartingLine = 6
        if (team) teamStartingLine = 1
        if (team && x == pastPosX + 2 && y == pastPosY && pastPosY == teamStartingLine && BOARD[x][y] == ' ' && BOARD[x-1][y-1] == ' ') return true
        //not working on black
        if (!team && x == pastPosX - 2 && y == pastPosY && pastPosY == teamStartingLine && BOARD[x][y] == ' ' && BOARD[x+1][y+1] == ' ') return true


        //Capture
        if (x == pastPosX+1 && (y == pastPosY+1 || y == pastPosY-1) && team != utils.isWhite(BOARD[x][y])) return true
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