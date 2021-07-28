package com.whyx.openchess.interfaces.model.board;

import com.whyx.whyxcommons.collections.ImmutableList;

/**
 * @author Sam Wykes.
 * Interface representing the state of a board in a game of chess.
 */
public interface IBoardState {

    /**
     * Get the cells currently in the board.
     *
     * @return 2D list of {@link ICell} objects.
     */
    ImmutableList<ImmutableList<ICell>> getCells();

}
