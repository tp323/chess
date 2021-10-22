class Notations {

    fun algebraicNotation(position: String): Boolean{
        val pastPos = "" + position.subSequence(0, 2)

        if(utils.isEmpty(pastPos)) {
            println("Can't Print")
            return false
        }
        val nextPos = "" + position.subSequence(3, 5)

        //TODO: CHECK IF PIECE THERE AND TO WHICH POSITIONS IT CAN MOVE
        /*val gamePiece = GamePiece(utils.isWhite(coordinates(pastPos)),BOARD[coordinateX(pastPos)][coordinateY(pastPos)],coordinateX(pastPos))

        gamePiece.team =


        if (gamePiece.team) gamePiece.piece = utils.convertToLowerCase(piece)
        if (!gamePiece.team) gamePiece.piece = piece

        gamePiece.pastPosX = coordinateX(pastPos)
        gamePiece.pastPosY = coordinateY(pastPos)*/

        /*if(player != gamePiece.team) {
            println("Not Current Player")
            return false
        }

        if (gamePiece.possibleMoves(coordinateX(nextPos),coordinateY(nextPos))) move(pastPos,nextPos)
        else {
            println("Move is Not Possible")
            return false
        }*/
        return true
    }
}