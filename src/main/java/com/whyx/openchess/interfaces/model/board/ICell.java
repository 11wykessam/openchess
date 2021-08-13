package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Optional;

/**
 * @param <T> The type of location being stored by the cell.
 * @author Sam Wykes.
 * Interface representing a cell in a board game, that may contain a piece.
 */
public interface ICell<T extends ILocation> {

    /**
     * Get the piece in the cell, if present.
     *
     * @return {@link Optional} that may contain {@link IPiece} object.
     */
    Optional<IPiece<T>> getPiece();

    /**
     * Get the location of the piece.
     *
     * @return {@link ILocation} object.
     */
    T getLocation();

}
