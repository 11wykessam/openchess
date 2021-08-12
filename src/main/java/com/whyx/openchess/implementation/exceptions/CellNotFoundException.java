package com.whyx.openchess.implementation.exceptions;

import com.whyx.openchess.implementation.model.board.Cell;

/**
 * @author Sam Wykes.
 * Exception thrown when {@link Cell} object not found.
 */
public class CellNotFoundException extends RuntimeException {

    public CellNotFoundException() {
        super("cell not found");
    }

}
