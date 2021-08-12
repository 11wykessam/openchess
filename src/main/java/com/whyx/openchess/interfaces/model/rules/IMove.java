package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.interfaces.model.board.ICell;

/**
 * @author Sam Wykes.
 * Class that represents a move in a game of chess.
 */
public interface IMove {

    /**
     * The start cell of the move.
     *
     * @return {@link ICell} object.
     */
    ICell getStart();

    /**
     * The destination cell of the move.
     *
     * @return {@link ICell} object.
     */
    ICell getDestination();

}
