package com.whyx.openchess.implementation.model.board.location;

import com.whyx.openchess.interfaces.model.board.ILocation;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class represents a location in a 2D plane.
 */
public class TwoDimensionalLocation implements ILocation {

    private final int x;
    private final int y;

    /**
     * @param builder Builder responsible for constructing object.
     */
    public TwoDimensionalLocation(final TwoDimensionalLocationBuilder builder) {
        this.x = builder.xSupplier.get();
        this.y = builder.ySupplier.get();
    }

    /**
     * Getter for the x coordinate of the cell.
     *
     * @return int.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter for y coordinate of the cell.
     *
     * @return int.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Create instance of builder.
     *
     * @return {@link TwoDimensionalLocationBuilder} object.
     */
    public static TwoDimensionalLocationBuilder builder() {
        return new TwoDimensionalLocationBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class responsible for building {@link TwoDimensionalLocation} objects.
     */
    public static class TwoDimensionalLocationBuilder {

        private Supplier<Integer> xSupplier;
        private Supplier<Integer> ySupplier;

        public TwoDimensionalLocationBuilder withXSupplier(final Supplier<Integer> xSupplier) {
            requireNonNull(xSupplier, "xSupplier must not be null");
            this.xSupplier = xSupplier;
            return this;
        }

        public TwoDimensionalLocationBuilder withYSupplier(final Supplier<Integer> ySupplier) {
            requireNonNull(ySupplier, "ySupplier must not be null");
            this.ySupplier = ySupplier;
            return this;
        }

        public TwoDimensionalLocation build() {
            requireNonNull(xSupplier, "xSupplier must not be null");
            requireNonNull(ySupplier, "ySupplier must not be null");
            return new TwoDimensionalLocation(this);
        }
    }

}
