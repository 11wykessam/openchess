package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.ICell;
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
        this.x = builder.xSupplier.get();
        this.y = builder.ySupplier.get();
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
     * @return {@link CellBuilder} object.
     */
    public static CellBuilder builder() {
        return new CellBuilder();
    }

    /**
     * @author Sam Wykes.
     * Builder class responsible for generating instances of {@link Cell}.
     */
    public static class CellBuilder implements Builder<ICell> {

        private Supplier<Integer> xSupplier;
        private Supplier<Integer> ySupplier;
        private IPiece piece;

        /**
         * Build the instance.
         *
         * @return {@link ICell} object.
         */
        public ICell build() {
            requireNonNull(xSupplier, "xSupplier must not be null");
            requireNonNull(ySupplier, "ySupplier must not be null");
            return new Cell(this);
        }

        /**
         * Provide the builder with x coordinate for the cell.
         *
         * @param xSupplier {@link Supplier} object responsible for providing the x coordinate.
         * @return {@link CellBuilder} object.
         */
        public CellBuilder withX(Supplier<Integer> xSupplier) {
            requireNonNull(xSupplier, "xSupplier must not be null");
            this.xSupplier = xSupplier;
            return this;
        }

        /**
         * Provide the builder with y coordinate for the cell.
         *
         * @param ySupplier {@link Supplier} object responsible for providing the y coordinate.
         * @return {@link CellBuilder} object.
         */
        public CellBuilder withY(Supplier<Integer> ySupplier) {
            requireNonNull(ySupplier, "ySupplier must not be null");
            this.ySupplier = ySupplier;
            return this;
        }

        /**
         * Provide the builder with an {@link IPiece} object.
         *
         * @param piece The {@link IPiece} object being placed in the cell.
         * @return {@link CellBuilder} object.
         */
        public CellBuilder withPiece(IPiece piece) {
            requireNonNull(piece, "piece must not be null");
            this.piece = piece;
            return this;
        }
    }

}
