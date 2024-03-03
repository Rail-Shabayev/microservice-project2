package org.project.exception;


/**
 *  Exception that thrown when product is not found in database
 */
public class OrderNotFoundException extends Exception {
    /**
     * overrides default constructor and prints message
     * @param message message to be printed to user
     */
    public OrderNotFoundException(String message) {
        super(message);
    }
}
