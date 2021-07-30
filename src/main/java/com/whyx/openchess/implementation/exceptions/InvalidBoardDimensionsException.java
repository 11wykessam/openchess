package com.whyx.openchess.implementation.exceptions;

/**
 * @author Sam Wykes.
 * Exception thrown when an attempt is made to create a non-rectangular board.
 */
public class InvalidBoardDimensionsException extends RuntimeException {

    public InvalidBoardDimensionsException() {
        super("Boards must have rectangular dimensions.");
    }

}
