package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ICellState;

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

    private Cell(CellBuilder builder) {
        this.cellState = builder.cellState;
        this.x = builder.xSupplier.get();
        this.y = builder.ySupplier.get();
    }

    /**
     * Getter for cellState.
     *
     * @return The {@link CellState} of the cell.
     */
    @Override
    public ICellState getState() {
        return this.cellState;
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
     *
     * @return int.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Return a builder object.
     *
     * @return {@link Builder} object.
     */
    public static CellBuilder builder() {
        return new CellBuilder();
    }

    /**
     * Builder class for {@link Cell}.
     */
    static class CellBuilder implements Builder<ICell> {

        private ICellState cellState;
        private Supplier<Integer> xSupplier;
        private Supplier<Integer> ySupplier;

        public Cell build() {
            requireNonNull(cellState, "cellState must not be null");
            requireNonNull(xSupplier, "xSupplier must not be null");
            requireNonNull(ySupplier, "ySupplier must not be null");
            return new Cell(this);
        }

        public CellBuilder withCellState(ICellState cellState) {
            requireNonNull(cellState, "cellState must not be null");
            this.cellState = cellState;
            return this;
        }

        public CellBuilder withXSupplier(Supplier<Integer> xSupplier) {
            requireNonNull(xSupplier, "xSupplier must not be null");
            this.xSupplier = xSupplier;
            return this;
        }

        public CellBuilder withYSupplier(Supplier<Integer> ySupplier) {
            requireNonNull(ySupplier, "ySupplier must not be null");
            this.ySupplier = ySupplier;
            return this;
        }
    }
}
