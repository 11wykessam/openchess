package com.whyx.openchess.implementation.model.rule.moverule;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.rules.IMove;

import static java.util.Objects.requireNonNull;

/**
 * @param <T> Type of location being stored by the move's cells.
 * @author Sam Wykes.
 * Class represented a move in a board game.
 */
public class Move<T extends ILocation> implements IMove<T> {

    private final ICell<T> start;
    private final ICell<T> destination;

    /**
     * Constructor.
     *
     * @param builder {@link MoveBuilder} being used.
     */
    private Move(final MoveBuilder<T> builder) {
        this.start = builder.start;
        this.destination = builder.destination;
    }

    /**
     * Get the start location of the move.
     *
     * @return {@link ICell} object.
     */
    @Override
    public ICell<T> getStart() {
        return this.start;
    }

    /**
     * Get the destination location of the move.
     *
     * @return {@link ICell} object.
     */
    @Override
    public ICell<T> getDestination() {
        return this.destination;
    }

    /**
     * Create an instance of the builder.
     *
     * @return {@link MoveBuilder} object.
     */
    public static <U extends ILocation> MoveBuilder<U> builder() {
        return new MoveBuilder<>();
    }

    /**
     * Class used to build {@link Move} instances.
     *
     * @param <U> The type of location being stored by the move's cells.
     */
    public static class MoveBuilder<U extends ILocation> {

        private ICell<U> start;
        private ICell<U> destination;

        public MoveBuilder<U> withStart(final ICell<U> start) {
            requireNonNull(start, "start must not be null");
            this.start = start;
            return this;
        }

        public MoveBuilder<U> withDestination(final ICell<U> destination) {
            requireNonNull(destination, "destination must not be null");
            this.destination = destination;
            return this;
        }

        public IMove<U> build() {
            requireNonNull(start, "start must not be null");
            requireNonNull(destination, "destination must not be null");
            return new Move<>(this);
        }
    }
}
