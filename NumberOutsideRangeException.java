public class NumberOutsideRangeException extends Exception{
    NumberOutsideRangeException(int n){
        super("Only numbers in range 1 to " + n);
    }
}