package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.whyxcommons.collections.ImmutableList;

/**
 * @author Sam Wykes.
 * Interface representing a board in a game of chess.
 */
public interface IBoard {

    /**
     * Getter for the cells on the board.
     *
     * @return 2D {@link ImmutableList} containing the cells on the board.
     */
    ImmutableList<ImmutableList<ICell>> getCells();

    /**
     * Getter for the width of the board.
     *
     * @return int.
     */
    int getWidth();

    /**
     * Getter for the height of the board.
     *
     * @return int.
     */
    int getHeight();

    /**
     * Place an {@link IPiece} object on a certain cell on the board.
     *
     * @param x     x coordinate of cell the piece is being placed on.
     * @param y     y coordiante of cell the piece is being placed on.
     * @param piece {@link IPiece} being placed.
     * @return {@link IBoard} resulting from the change.
     */
    IBoard placePiece(int x, int y, IPiece piece);

}
