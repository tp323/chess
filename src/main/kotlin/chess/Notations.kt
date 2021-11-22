package chess

fun algebraicNotation(position: String): Boolean {
    val pastPos = "" + position.subSequence(0, 2)

    if (isEmpty(pastPos)) {
        println("Can't Print")
        return false
    }
    val nextPos = "" + position.subSequence(3, 5)

    //TODO: CHECK IF PIECE THERE AND TO WHICH POSITIONS IT CAN MOVE
    val gamePiece = GamePiece(
        isWhite(coordinates(pastPos)),
        BOARD[coordinateX(pastPos)][coordinateY(pastPos)],
        coordinateX(pastPos),
        coordinateY(pastPos)
    )

    gamePiece.team = player
    val piece = BOARD[coordinateX(pastPos)][coordinateY(pastPos)]

    if (gamePiece.team) gamePiece.piece = convertToLowerCase(piece)
    if (!gamePiece.team) gamePiece.piece = piece

    gamePiece.pastPosX = coordinateX(pastPos)
    gamePiece.pastPosY = coordinateY(pastPos)

    if (player != gamePiece.team) {
        println("Not Current Player")
        return false
    }

    if (gamePiece.possibleMoves(coordinateX(nextPos), coordinateY(nextPos))) move(pastPos, nextPos)
    else {
        println("Move is Not Possible")
        return false
    }
    return true

}