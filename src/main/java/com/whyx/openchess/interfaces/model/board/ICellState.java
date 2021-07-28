package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Optional;

/**
 * @author Sam Wykes.
 * Interface representing the state of a cell in a game of chess.
 */
public interface ICellState {

    /**
     * Get the piece in the cell, if it exists.
     *
     * @return {@link Optional} representing the piece in the cell.
     */
    Optional<IPiece> getPiece();

}
