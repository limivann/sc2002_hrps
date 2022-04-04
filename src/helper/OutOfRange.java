package src.helper;

/**
 * Thrown by a {@code Scanner} to indicate that the token
 * retrieved is not within the specified range.
 */
public class OutOfRange extends Exception{
    
    /** 
     * Constructor that initialises the error message
     */
    public OutOfRange(){
        super("Input is out of allowed range");
    }

    /**
     * Overrided constructor that initialises the error message with the specified message
     * @param message error message to be displayed
     */
    public OutOfRange(String message){
        super(message);
    }
}
