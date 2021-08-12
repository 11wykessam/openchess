package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @author Sam Wykes.
 * Interface that represents a board in a board game.
 */
public interface IBoard {

    /**
     * Gets the cells on the board.
     *
     * @return {@link Iterator} containing8 {@link ICell} objects.
     */
    Stream<ICell> getCells();

    /**
     * Place a given piece on a cell.
     *
     * @param cell  The {@link ICell} the piece is being placed on.
     * @param piece The {@link IPiece} being placed.
     */
    IBoard placePieceOnCell(ICell cell, IPiece piece) throws CellNotFoundException;
}
