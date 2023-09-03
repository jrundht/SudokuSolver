public class CharCountMismatchException extends Exception{
    CharCountMismatchException(int n){
        super("The number of characters in a "+ n + "x" + n + " sudoku has to be: " + n*n);
    }

}