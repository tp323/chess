class Notations {

    fun algebraicNotation(position: String){
        val pastPos = "" + position.subSequence(0, 2)
        val utils = Utils()
        if(utils.isEmpty(pastPos)) {
            println("Can't Print")
            return
        }
        val nextPos = "" + position.subSequence(3, 5)

        //TODO: CHECK IF PIECE THERE AND TO WHICH POSITIONS IT CAN MOVE
        //check if piece from current player

        move(pastPos,nextPos)
    }
}