package com.whyx.openchess.model.interfaces.board;

import com.whyx.openchess.model.interfaces.board.pieces.IPiece;

import java.util.Optional;

/**
 * Interface representing a cell in chess.
 */
public interface ICell {

    /**
     * Gets piece in the cell, if non-empty.
     * @return {@link Optional< IPiece >} object.
     */
    Optional<IPiece> getPiece();

    /**
     * Gets the emptiness state of the cell.
     * @return boolean.
     */
    boolean isEmpty();

}
