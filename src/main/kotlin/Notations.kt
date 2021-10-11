class Notations {

    fun algebraicNotation(position: String): Boolean{
        val pastPos = "" + position.subSequence(0, 2)

        if(utils.isEmpty(pastPos)) {
            println("Can't Print")
printBoardSmall()
            return false
        }
        val nextPos = "" + position.subSequence(3, 5)

        //TODO: CHECK IF PIECE THERE AND TO WHICH POSITIONS IT CAN MOVE
        val GamePiece = GamePiece()
        GamePiece.team = utils.isWhite(coordinates(pastPos))

        /*if(player != utils.isUpperCase(coordinates(pastPos))) {
            println("Not Current Player")
            return false
        }*/

        if(player != GamePiece.team) {
            println("Not Current Player")
            return false
        }

        move(pastPos,nextPos)
        return true
    }
}