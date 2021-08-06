package com.whyx.openchess.implementation.exceptions;

import com.whyx.openchess.interfaces.model.board.IBoard;

/**
 * @author Sam Wykes.
 * Exception thrown when attempt is made to create a {@link IBoard} object with illegal dimensions.
 */
public class InvalidBoardDimensionException extends RuntimeException {

    public InvalidBoardDimensionException() {
        super("Board dimensions invalid");
    }

}
