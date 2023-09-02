public class CharCountMismatchException extends Exception{
    CharCountMismatchException(int n){
        super("The number of characters in the grid has to be: " + n*n);
    }

}