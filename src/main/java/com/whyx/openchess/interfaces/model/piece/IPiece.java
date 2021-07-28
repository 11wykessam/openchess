package com.whyx.openchess.interfaces.model.piece;

import com.whyx.openchess.interfaces.common.PieceColour;

/**
 * @author Sam Wykes
 * Interface representing a pice in a game of chess.
 */
public interface IPiece {

    /**
     * Get the state of the piece.
     *
     * @return {@link IPieceState} object.
     */
    IPieceState getState();

    /**
     * Get the colour of the piece.
     *
     * @return {@link PieceColour} enum.
     */
    PieceColour getColour();

}
