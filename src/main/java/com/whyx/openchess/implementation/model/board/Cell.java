package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ICellBuilder;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class that represents a cell on a board in a game of chess.
 */
public class Cell implements ICell {

    private final int x;
    private final int y;
    private final IPiece piece;

    public Cell(CellBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.piece = builder.piece;
    }

    /**
     * Getter for x.
     *
     * @return int.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Getter for y.
     * @return int.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Getter for piece.
     *
     * @return {@link Optional} that may contain {@link IPiece} object.
     */
    @Override
    public Optional<IPiece> getPiece() {
        return Optional.ofNullable(piece);
    }

    /**
     * Generate instance of builder.
     *
     * @return {@link ICellBuilder} object.
     */
    public static ICellBuilder builder() {
        return new CellBuilder();
    }

    /**
     * @author Sam Wykes.
     * Builder class responsible for generating instances of {@link Cell}.
     */
    public static class CellBuilder implements ICellBuilder {

        private int x;
        private int y;
        private IPiece piece;

        /**
         * Build the instance.
         *
         * @return {@link ICell} object.
         */
        @Override
        public ICell build() {
            return new Cell(this);
        }

        /**
         * Provide the builder with x coordinate for the cell.
         *
         * @param xSupplier {@link Supplier} object responsible for providing the x coordinate.
         * @return {@link ICellBuilder} object.
         */
        @Override
        public ICellBuilder withX(Supplier<Integer> xSupplier) {
            requireNonNull(xSupplier, "xSupplier must not be null");
            this.x = xSupplier.get();
            return this;
        }

        /**
         * Provide the builder with y coordinate for the cell.
         *
         * @param ySupplier {@link Supplier} object responsible for providing the y coordinate.
         * @return {@link ICellBuilder} object.
         */
        @Override
        public ICellBuilder withY(Supplier<Integer> ySupplier) {
            requireNonNull(ySupplier, "ySupplier must not be null");
            this.y = ySupplier.get();
            return this;
        }

        /**
         * Provide the builder with an {@link IPiece} object.
         *
         * @param piece The {@link IPiece} object being placed in the cell.
         * @return {@link ICellBuilder} object.
         */
        @Override
        public ICellBuilder withPiece(IPiece piece) {
            requireNonNull(piece, "piece must not be null");
            this.piece = piece;
            return this;
        }
    }

}
