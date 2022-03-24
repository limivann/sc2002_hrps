package src.helper;

public class OutOfRange extends Exception{
    
    public OutOfRange(){
        super("Input is out of allowed range");
    }

    public OutOfRange(String message){
        super(message);
    }
}
