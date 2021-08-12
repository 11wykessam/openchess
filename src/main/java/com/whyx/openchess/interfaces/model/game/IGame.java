package com.whyx.openchess.interfaces.model.game;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.rules.IMove;

/**
 * @author Sam Wykes.
 * Interface representing a board game.
 */
public interface IGame {

    /**
     * Get the board being played on.
     *
     * @return {@link IBoard} object.
     */
    IBoard getBoard();

    /**
     * Check whether a given move is legal.
     *
     * @param piece The {@link IPiece} being moved.
     * @param move  The {@link IMove} being made.
     * @return boolean.
     */
    boolean isMoveLegal(IPiece piece, IMove move);
}
