package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class that represents a cell in a board game.
 */
public class Cell implements ICell {

    private final IPiece piece;
    private final ILocation location;

    /**
     * @param builder The builder being used to create the object.
     */
    private Cell(CellBuilder builder) {
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
    public ILocation getLocation() {
        return this.location;
    }

    /**
     * Create an instance of the builder.
     *
     * @return {@link CellBuilder} object.
     */
    public static CellBuilder builder() {
        return new CellBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class responsible for producing {@link Cell} instances.
     */
    public static class CellBuilder {

        private IPiece piece;
        private ILocation location;

        public CellBuilder withPiece(IPiece piece) {
            requireNonNull(piece, "piece must not be null");
            this.piece = piece;
            return this;
        }

        public CellBuilder withLocation(ILocation location) {
            requireNonNull(location, "location must not be null");
            this.location = location;
            return this;
        }

        public ICell build() {
            requireNonNull(location, "location must not be null");
            return new Cell(this);
        }
    }
}
