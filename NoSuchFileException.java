public class NoSuchFileException extends Exception{
    NoSuchFileException(){
        super("Cannot find file");
    }
}