package com.whyx.openchess.interfaces.model.board;

import com.whyx.whyxcommons.collections.ImmutableList;

/**
 * @author Sam Wykes.
 * Interface representing a board in a game of chess.
 */
public interface IBoard {

    /**
     * Getter for the cells on the board.
     *
     * @return 2D {@link ImmutableList} containing the cells on the board.
     */
    ImmutableList<ImmutableList<ICell>> getCells();

}
