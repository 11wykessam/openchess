package com.whyx.openchess.interfaces.model.game;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;

/**
 * @param <T> The type of location being used in the game.
 * @author Sam Wykes.
 * Interface representing a board game.
 */
public interface IGame<T extends ILocation> {

    /**
     * Get the board being played on.
     *
     * @return {@link IBoard} object.
     */
    IBoard<T> getBoard();

    /**
     * Check whether a given move is legal.
     *
     * @param piece The {@link IPiece} being moved.
     * @param move  The {@link IMove} being made.
     * @return boolean.
     * @throws PieceNotFoundException thrown if the piece is not in the game.
     * @throws CellNotFoundException  thrown if either the start or destination cell not in the game.
     */
    boolean isMoveLegal(IPiece<T> piece, IMove<T> move) throws PieceNotFoundException, CellNotFoundException;
}
