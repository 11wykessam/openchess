package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;

/**
 * @author Sam Wykes.
 * Class that represents a move in a game of chess.
 */
public interface IMove<T extends ILocation> {

    /**
     * The start cell of the move.
     *
     * @return {@link ICell} object.
     */
    ICell<T> getStart();

    /**
     * The destination cell of the move.
     *
     * @return {@link ICell} object.
     */
    ICell<T> getDestination();

}
