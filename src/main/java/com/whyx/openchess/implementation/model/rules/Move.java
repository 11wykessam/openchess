package com.whyx.openchess.implementation.model.rules;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.rules.IMove;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class represented a move in a board game.
 */
public class Move implements IMove {

    private final ICell start;
    private final ICell destination;

    /**
     * Constructor.
     *
     * @param builder {@link MoveBuilder} being used.
     */
    private Move(final MoveBuilder builder) {
        this.start = builder.start;
        this.destination = builder.destination;
    }

    /**
     * Get the start location of the move.
     *
     * @return {@link ICell} object.
     */
    @Override
    public ICell getStart() {
        return this.start;
    }

    /**
     * Get the destination location of the move.
     *
     * @return {@link ICell} object.
     */
    @Override
    public ICell getDestination() {
        return this.destination;
    }

    /**
     * Create an instance of the builder.
     *
     * @return {@link MoveBuilder} object.
     */
    public static MoveBuilder builder() {
        return new MoveBuilder();
    }

    /**
     * Class used to build {@link Move} instances.
     */
    public static class MoveBuilder {

        private ICell start;
        private ICell destination;

        public MoveBuilder withStart(final ICell start) {
            requireNonNull(start, "start must not be null");
            this.start = start;
            return this;
        }

        public MoveBuilder withDestination(final ICell destination) {
            requireNonNull(destination, "destination must not be null");
            this.destination = destination;
            return this;
        }

        public IMove build() {
            requireNonNull(start, "start must not be null");
            requireNonNull(destination, "destination must not be null");
            return new Move(this);
        }
    }
}
