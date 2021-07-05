package com.whyx.openchess.model.interfaces.board;

import com.whyx.openchess.model.interfaces.board.pieces.IPiece;

import java.util.Set;

/**
 * @author Sam Wykes.
 * Interface representing a board in chess.
 */
public interface IBoard {

    /**
     * Gets the {@link ICell} at a given position.
     * @param x x coordinate of cell being accessed.
     * @param y y coordinate of cell being accessed.
     * @return {@link ICell} object.
     */
    ICell getCell(int x, int y);

    /**
     * Gets all {@link IPiece} objects on the board.
     * @return {@link Set} of {@link IPiece} objects.
     */
    Set<IPiece> getPieces();

    /**
     * Gets the width of the board.
     * @return int.
     */
    int getWidth();

    /**
     * Gets the height of the board.
     * @return int.
     */
    int getHeight();

}
