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
public class InvalidEmployeeException extends Exception{

    public InvalidEmployeeException() {
    }

    public InvalidEmployeeException(String message) {
        super(message);
    }

    public InvalidEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmployeeException(Throwable cause) {
        super(cause);
    }

    public InvalidEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
