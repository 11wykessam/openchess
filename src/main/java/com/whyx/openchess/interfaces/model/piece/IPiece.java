package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.common.PieceColour;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;

/**
 * @author Sam Wykes
 * Interface representing a pice in a game of chess.
 */
public interface IPiece {

    /**
     * Get the state of the piece.
     * @return {@link IPieceState} object.
     */
    IPieceState getState();

    /**
     * Get the colour of the piece.
     *
     * @return {@link PieceColour} enum.
     */
    PieceColour getColour();

    /**
     * Checks whether a given move is hypothetically legal.
     * This method doesn't consider whether the piece is actually on the start cell.
     *
     * @param board The {@link IBoard} the game is being played on.
     * @param start The {@link ICell} the move is being made from.
     * @param end   The {@link ICell} destination of the move.
     * @return boolean representing whether the move is possible.
     */
    boolean isMoveLegal(IBoard board, ICell start, ICell end);

}
