import java.util.*;
public class Project {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        char[][] board = new char[6][7];
        char player = 'O';
        boolean reset = true;
        int countX =0;
        int countO =0;
        System.out.println("Connect 4");
        while (reset == true) {
            setBoard(board);
            showGameBoard(board);
            do {
                //drop piece
                player = switchPlayer(player);
                System.out.println("\n Player " + player + ": Drop piece at column: ");
                int person = input.nextInt();
                //CHECK input is 1-7
                while (person <= 0 || person > 7) {
                    System.out.print("Input needs to be between column 1-7!!! \nDrop piece at column: \n");
                    person = input.nextInt();
                }
                while (dropPiece(board, person, player) == false) {
                    System.out.println("\nEnter new column: ");
                    person = input.nextInt();
                }

                showGameBoard(board);
            } while (checkWinner(board) == false);
            System.out.println("\nPlayer " + player + " is the WINNER WINNER CHICKEN DINNER!!!! WOoOoO DESTROYED\n");
            if(player == 'X')
                countX += 1;
            else if (player == 'O')
                countO += 1;
            System.out.println("Enter 0 to play again : _ \nEnter any number to end game: _");
            reset = resets(input.nextInt());
        }
        System.out.println("SCORE BOARD --> | Player X: " + countX +"| |Player O: " + countO +"|");
        if(countX > countO)
            System.out.println("Player X Wins");
        else
            System.out.println("Player O Wins");
    }

    public static char[][] setBoard(char[][] board){ //make empty board
        for (int row = 0; row < board.length; row++){
            Arrays.fill(board[row], 0, board[row].length, ' '); //empty '\u0000'
        }
        return board;
    }
    public static void showGameBoard(char[][]board){
        System.out.println();
        System.out.println("|_1_|_2_|_3_|_4_|_5_|_6_|_7_|");
        for (int row = 0; row < board.length; row++){
            System.out.print("|");
            for (int column = 0; column < board[row].length; column++){
                System.out.print(" " + board[row][column] +" |");
            }
            System.out.println();
        }
    }
    public static boolean dropPiece(char[][] board, int column, char player) { //player 'X' or 'O'
        //from the bottom row and if full go to the next row on top.
        if(board[0][column-1] != ' ') {
            System.out.println("INVALID INPUT CHEATER!! >:(  ROOAARR");
            return false;
        }
        for (int row = board.length - 1; row >= 0; row--) { //start from bottom check if filled and go up then break
            if (board[row][column - 1] == ' ') {
                board[row][column - 1] = player;
                return true;
            }
        }
        return true;
    }

    public static char switchPlayer(char currentPlayer){
        if (currentPlayer == 'X'){
            return 'O';
        }
        return 'X';
    }

    public static boolean checkWinner(char[][] board){
        //check horizontal
        for (int row = 0; row < board.length; row++){
            for (int column = 0; column < board[row].length - 3; column++){
                if ( board[row][column] != ' ' && board[row][column] == board[row][column + 1] && board[row][column + 1] == board[row][column + 2] && board[row][column + 2] == board[row][column + 3]){
                    return true;
                }
            }
        }
        //check vertical
        for (int column = 0; column < board[0].length; column++){
            for (int row = 0; row < board.length - 3; row++){
                if (board[row][column] != ' ' && board[row][column] == board[row+1][column] && board[row + 1][column] == board[row + 2][column] && board[row + 2][column] == board[row+3][column]){
                    return true;
                }
            }
        }

        //check major diag
        //from 3 of top left left side (down)
        for (int row = 0; row < board.length - 3; row++) {
            for (int column = 0; column < board[row].length - 3; column++) {
                if (board[row][column] != ' ' && board[row][column] == board[row + 1][column + 1] && board[row + 1][column + 1] == board[row + 2][column + 2] && board[row + 2][column + 2] == board[row + 3][column + 3]) {
                    return true;
                }
            }
        }
        //from 3 from top left right side (top)
        for (int column = 1; column < board[0].length - 3; column++) {
            for (int row = 0; row < board.length - 3; row++){
                if (board[row][column] != ' ' && board[row][column] == board[row + 1][column + 1] && board[row + 1][column + 1] == board[row + 2][column + 2] && board[row + 2][column + 2] == board[row + 3][column + 3]) {
                    return true;
                }
            }
        }
        //check minor diag down
        for (int row = 0; row < board.length - 3; row++) {
            for (int column = 6; column >= 3; column--) {
                if (board[row][column] != ' ' && board[row][column] == board[row + 1][column - 1] && board[row + 1][column - 1] == board[row + 2][column - 2] && board[row + 2][column - 2] == board[row + 3][column - 3]) {
                    return true;
                }
            }
        }
        //check minor diag top
        for (int column = 5; column <= 3; column--) {
            for (int row = 0; row < board.length - 3; row++){
                if (board[row][column] != ' ' && board[row][column] == board[row + 1][column - 1] && board[row + 1][column - 1] == board[row + 2][column - 2] && board[row + 2][column - 2] == board[row + 3][column - 3]) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean resets(int enter){
        if(enter == 0){
            return true;
        }else
            return false;
    }
}