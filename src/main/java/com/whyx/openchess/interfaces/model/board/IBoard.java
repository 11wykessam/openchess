package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @param <T> The type of location being stored on the board.
 * @author Sam Wykes.
 * Interface that represents a board in a board game.
 */
public interface IBoard<T extends ILocation> {

    /**
     * Gets the cells on the board.
     *
     * @return {@link Iterator} containing8 {@link ICell} objects.
     */
    Stream<ICell<T>> getCells();

    /**
     * Place a given piece on a cell.
     *
     * @param cell  The {@link ICell} the piece is being placed on.
     * @param piece The {@link IPiece} being placed.
     */
    IBoard<T> placePieceOnCell(ICell<T> cell, IPiece<T> piece) throws CellNotFoundException;

    /**
     * Checks whether a given cell exists on the board.
     *
     * @param cell {@link ICell} being checked for.
     * @return boolean.
     */
    boolean containsCell(ICell<T> cell);
}
