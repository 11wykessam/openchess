package com.whyx.openchess.interfaces.model.game;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.exceptions.IllegalMoveException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;

import java.util.Set;

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
     * @param piece       The {@link IPiece} being moved.
     * @param start       The {@link ICell} the move is being made from.
     * @param destination The {@link ICell} the move is being made to.
     * @return boolean.
     * @throws PieceNotFoundException thrown if the piece is not in the game.
     * @throws CellNotFoundException  thrown if either the start or destination cell not in the game.
     */
    boolean isMoveLegal(IPiece<T> piece, ICell<T> start, ICell<T> destination) throws PieceNotFoundException, CellNotFoundException;

    /**
     * Make a move.
     *
     * @param piece The {@link IPiece} being moved.
     * @param move  The {@link IMove} being made.
     * @return The resultant {@link IGame}.
     * @throws IllegalMoveException Thrown if the move being made is illegal.
     */
    IGame<T> makeMove(IPiece<T> piece, IMove<T> move) throws IllegalMoveException;

    /**
     * Get set of possible moves from a given cell.
     *
     * @param cell The {@link ICell} moves are being made from.
     * @return {@link Set} containing {@link IMove} objects.
     * @throws CellNotFoundException Thrown if the cell is not in the game.
     */
    Set<IMove<T>> getPossibleMovesFromCell(ICell<T> cell) throws CellNotFoundException;

    /**
     * Get the current state of the game.
     *
     * @return {@link IGameState} object.
     */
    IGameState getState();
}
