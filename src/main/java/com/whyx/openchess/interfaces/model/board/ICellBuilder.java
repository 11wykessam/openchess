package com.whyx.openchess.interfaces.model.board;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.function.Supplier;

/**
 * @author Sam Wykes.
 * Interface representing classes intended to build {@link ICell} instances.
 */
public interface ICellBuilder extends Builder<ICell> {

    /**
     * Provides an x value via a supplier to the builder.
     *
     * @param xSupplier {@link Supplier} object responsible for providing the x coordinate.
     * @return {@link ICellBuilder} object.
     */
    ICellBuilder withX(Supplier<Integer> xSupplier);

    /**
     * Provides an y value via a supplier to the builder.
     *
     * @param ySupplier {@link Supplier} object responsible for providing the y coordinate.
     * @return {@link ICellBuilder} object.
     */
    ICellBuilder withY(Supplier<Integer> ySupplier);

    /**
     * Provides a {@link IPiece} object to the builder.
     *
     * @param piece The {@link IPiece} object being placed in the cell.
     * @return {@link ICellBuilder} object.
     */
    ICellBuilder withPiece(IPiece piece);
}
