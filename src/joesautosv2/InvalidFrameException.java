/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

/**
 * Exception is thrown when the user enters an invalid employee role
 * @author ryan
 */
public class InvalidFrameException extends Exception{

    public InvalidFrameException() {
    }

    public InvalidFrameException(String message) {
        super(message);
    }

    public InvalidFrameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFrameException(Throwable cause) {
        super(cause);
    }

    public InvalidFrameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
