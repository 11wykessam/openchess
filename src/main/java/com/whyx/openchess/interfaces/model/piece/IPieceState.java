package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.model.board.ICell;

import java.util.Optional;

/**
 * Interface representing the state of a piece in a game of chess.
 */
public interface IPieceState {

    /**
     * Get the cell the piece is in.
     *
     * @return {@link ICell} cell object.
     */
    Optional<ICell> getCell();

}
