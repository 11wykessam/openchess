package com.whyx.openchess.exceptions;

import com.whyx.openchess.model.interfaces.board.ICell;

/**
 * @author Sam Wykes
 * Thrown when an attempt is made to access a {@link ICell} that doesn't exist.
 */
public class NoSuchCellException extends RuntimeException {

    public NoSuchCellException(int x, int y) {
        super("No such cell found at position: " + x + ", " + y);
    }

    public NoSuchCellException() {
        super("Cell does not exist.");
    }

}
