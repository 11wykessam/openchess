package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * @param <T> The type of location being stored by the cell.
 * @author Sam Wykes.
 * Class that represents a cell in a board game.
 */
public class Cell<T extends ILocation> implements ICell<T> {

    private final IPiece piece;
    private final T location;

    /**
     * @param builder The builder being used to create the object.
     */
    private Cell(final CellBuilder<T> builder) {
        this.piece = builder.piece;
        this.location = builder.location;
    }

    /**
     * Get the piece on the cell, if present.
     *
     * @return {@link Optional} object that may contain a {@link IPiece} object.
     */
    @Override
    public Optional<IPiece> getPiece() {
        return Optional.ofNullable(piece);
    }

    /**
     * Get the location of the cell.
     *
     * @return {@link ILocation} object.
     */
    @Override
    public T getLocation() {
        return this.location;
    }

    /**
     * Create an instance of the builder.
     *
     * @return {@link CellBuilder} object.
     */
    public static <U extends ILocation> CellBuilder<U> builder() {
        return new CellBuilder<U>();
    }

    /**
     * @param <U> The type of location being stored by the cells being built.
     * @author Sam Wykes.
     * Class responsible for producing {@link Cell} instances.
     */
    public static class CellBuilder<U extends ILocation> {

        private IPiece piece;
        private U location;

        public CellBuilder<U> withPiece(final IPiece piece) {
            requireNonNull(piece, "piece must not be null");
            this.piece = piece;
            return this;
        }

        public CellBuilder<U> withLocation(final U location) {
            requireNonNull(location, "location must not be null");
            this.location = location;
            return this;
        }

        public ICell<U> build() {
            requireNonNull(location, "location must not be null");
            return new Cell<U>(this);
        }
    }
}
