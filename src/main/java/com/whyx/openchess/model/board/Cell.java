package com.whyx.openchess.model.board;

import com.whyx.openchessinterface.model.board.ICell;
import com.whyx.openchessinterface.model.board.ICellState;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes
 * Class representing a cell in chess.
 */
public class Cell implements ICell {

    private final ICellState cellState;
    private final int x;
    private final int y;

    public Cell(Builder builder) {
        this.cellState = builder.cellState;
        this.x = builder.xSupplier.get();
        this.y = builder.ySupplier.get();
    }

    @Override
    public ICellState getState() {
        return this.cellState;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Return the builder object.
     *
     * @return {@link Builder} object.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for {@link Cell}.
     */
    static class Builder {

        private ICellState cellState;
        private Supplier<Integer> xSupplier;
        private Supplier<Integer> ySupplier;

        public Cell build() {
            requireNonNull(cellState, "cellState must not be null");
            requireNonNull(xSupplier, "xSupplier must not be null");
            requireNonNull(ySupplier, "ySupplier must not be null");
            return new Cell(this);
        }

        public Builder withCellState(ICellState cellState) {
            requireNonNull(cellState, "cellState must not be null");
            this.cellState = cellState;
            return this;
        }

        public Builder withXSupplier(Supplier<Integer> xSupplier) {
            requireNonNull(xSupplier, "xSupplier must not be null");
            this.xSupplier = xSupplier;
            return this;
        }

        public Builder withYSupplier(Supplier<Integer> ySupplier) {
            requireNonNull(ySupplier, "ySupplier must not be null");
            this.ySupplier = ySupplier;
            return this;
        }


    }
}
