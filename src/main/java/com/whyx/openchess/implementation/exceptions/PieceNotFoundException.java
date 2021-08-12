package com.whyx.openchess.implementation.exceptions;

import com.whyx.openchess.interfaces.model.piece.IPiece;

/**
 * @author Sam Wykes.
 * Exception thrown when {@link IPiece} cannot be found.
 */
public class PieceNotFoundException extends RuntimeException {

    public PieceNotFoundException() {
        super("piece not found");
    }

}
