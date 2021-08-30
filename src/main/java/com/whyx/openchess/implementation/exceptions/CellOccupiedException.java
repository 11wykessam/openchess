package com.whyx.openchess.implementation.exceptions;

/**
 * @author Sam Wykes.
 * Exception thrown when attempt is made to place a piece on an occupied cell.
 */
public class CellOccupiedException extends RuntimeException {

    public CellOccupiedException() {
        super("cell occupied");
    }

}
