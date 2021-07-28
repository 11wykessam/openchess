package com.whyx.openchess.interfaces.model.game;

import com.whyx.openchess.interfaces.model.board.IBoard;

/**
 * @author Sam Wykes
 * Interface representing a game of chess.
 */
public interface IGame {

    /**
     * Get the board currently being played on.
     *
     * @return {@link IBoard} object.
     */
    IBoard getBoard();

}
