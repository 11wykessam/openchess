package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;

/**
 * @author Sam Wykes.
 * Interface representing a piece in chess.
 */
public interface IPiece {

    /**
     * Checks whether a given move is legal for a piece.
     *
     * @param board       The {@link IBoard} the move is being made on.
     * @param start       Starting point of the move.
     * @param destination End point of the move.
     * @return
     */
    boolean isMoveLegal(IBoard board, ICell start, ICell destination);

    /**
     * Get the colour of the piece.
     *
     * @return {@link PieceColour} enum.
     */
    PieceColour getColour();

}
