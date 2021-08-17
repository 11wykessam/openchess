package com.whyx.openchess.implementation.exceptions;

/**
 * @author Sam Wykes.
 * Exception thrown when an attempt is made to make an illegal move.
 */
public class IllegalMoveException extends RuntimeException {

    public IllegalMoveException() {
        super("illegal move");
    }

}
