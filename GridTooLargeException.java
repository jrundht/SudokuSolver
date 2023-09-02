public class GridTooLargeException extends Exception{
    GridTooLargeException(){
        super("Maximum size of grid is 64 x 64");
    }
}