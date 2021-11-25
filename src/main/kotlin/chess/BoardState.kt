package chess

fun resetBoard() {
    BOARD[0] = charArrayOf('R','K','B','Q','A','B','K','R')
    BOARD[1] = charArrayOf('P','P','P','P','P','P','P','P')
    BOARD[6] = charArrayOf('p','p','p','p','p','p','p','p')
    BOARD[7] = charArrayOf('r','k','b','q','a','b','k','r')
    for(n in 2..5) BOARD[n] = charArrayOf(' ',' ',' ',' ',' ',' ',' ',' ')
}