/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

public class InvalidMotorbikeException extends Exception{

    public InvalidMotorbikeException() {
    }

    public InvalidMotorbikeException(String message) {
        super(message);
    }

    public InvalidMotorbikeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMotorbikeException(Throwable cause) {
        super(cause);
    }

    public InvalidMotorbikeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
