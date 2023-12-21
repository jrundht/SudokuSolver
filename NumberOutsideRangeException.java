public class NumberOutsideRangeException extends Exception{
    NumberOutsideRangeException(char c , int n){
        super("Character: "+ c + " outside range, only characters in range 1 to " + n);
    }
}