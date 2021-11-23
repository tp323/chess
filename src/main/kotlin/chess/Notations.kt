package chess

fun algebraicNotationCheck(position: String): Boolean{
    if (position.length!=5) {
        println("Wrong size String for Algebraic Notation")
        return false
    }
    val pastPos = isChar(position[0]) && isInt(position[1])
    val nextPos = isChar(position[3]) && isInt(position[4])
    val algebraicChecker = pastPos && nextPos
    if (!algebraicChecker){
        println("Isn't in Algebraic Notation")
        return false
    }
    for (n in 0..1) {
        if (position[n*3] !in 'a'..'h') return false
        if (position[1 + n * 3] !in '1'..'8') return false
    }
    return true
}
