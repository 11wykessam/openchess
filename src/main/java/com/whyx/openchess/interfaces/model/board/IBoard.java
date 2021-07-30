package com.whyx.openchess.interfaces.model.board;

/**
 * @author Sam Wykes
 * Interface representing a board in chess.
 */
public interface IBoard {

    /**
     * Get the current state of the board.
     *
     * @return {@link IBoardState} object.
     */
    IBoardState getState();


}
