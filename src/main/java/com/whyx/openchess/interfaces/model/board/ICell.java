package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Optional;

/**
 * @author Sam Wykes.
 * Interface representing a cell in chess.
 */
public interface ICell {

    /**
     * The x coordinate of the cell.
     *
     * @return int.
     */
    int getX();

    /**
     * The y coordinate of the cell.
     *
     * @return int.
     */
    int getY();

    /**
     * The chess piece currently in the cell.
     *
     * @return {@link IPiece} object.
     */
    Optional<IPiece> getPiece();

}
