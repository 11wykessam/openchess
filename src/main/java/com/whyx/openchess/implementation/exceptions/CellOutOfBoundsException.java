package com.whyx.openchess.implementation.exceptions;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;

/**
 * @author Sam Wykes.
 * Exception thrown when attempt is made to access a {@link ICell} in a {@link IBoard} is out of bounds.
 */
public class CellOutOfBoundsException extends RuntimeException {

    public CellOutOfBoundsException() {
        super("Cell out of bounds");
    }

}
