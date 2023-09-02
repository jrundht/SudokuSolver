public class IllegalCharInFileException extends Exception{
    IllegalCharInFileException(char n){
        super(n + " is not a legal character in sudoku");
    }
}