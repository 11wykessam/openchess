package com.whyx.openchess.model.interfaces.board.pieces;

import com.whyx.openchess.model.implementation.board.pieces.Team;
import com.whyx.openchess.model.interfaces.board.IBoard;

/**
 * @author Sam Wykes.
 * Interface representing piece in chess.
 */
public interface IPiece {

    /**
     * Gets the x coordinate of the piece.
     * @return int.
     */
    int getX();

    /**
     * Gets the y coordinate of the piece.
     * @return int.
     */
    int getY();

    /**
     * Gets the team the piece is on.
     * @return {@link Team} enum.
     */
    Team getTeam();

    /**
     * Checks if a given move is legal for the piece.
     * @param x x coordinate of move destination.
     * @param y y coordinate of move destination.
     * @param currentBoard The current state of the board.
     * @return boolean.
     */
    boolean isLegalMove(int x, int y, IBoard currentBoard);

    /**
     * Checks whether a given piece has moved.
     * @return boolean.
     */
    boolean hasMoved();

}
