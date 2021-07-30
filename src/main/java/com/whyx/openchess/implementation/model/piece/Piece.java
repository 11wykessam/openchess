package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.common.PieceColour;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceState;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class used to represent
 */
public abstract class Piece implements IPiece {

    private final IPieceState state;
    private final PieceColour colour;

    protected Piece(PieceBuilder builder) {
        this.state = builder.state;
        this.colour = builder.colour;
    }

    /**
     * Getter for state.
     *
     * @return {@link IPieceState} object.
     */
    @Override
    public IPieceState getState() {
        return this.state;
    }

    /**
     * Getter for colour.
     *
     * @return {@link PieceColour} enum.
     */
    @Override
    public PieceColour getColour() {
        return this.colour;
    }

    /**
     * Builder responsible for building {@link Piece} class.
     */
    static abstract class PieceBuilder implements Builder<IPiece> {

        protected IPieceState state;
        protected PieceColour colour;

        public PieceBuilder withState(IPieceState state) {
            requireNonNull(state, "state must not be null");
            this.state = state;
            return this;
        }

        public PieceBuilder withColour(PieceColour colour) {
            requireNonNull(colour, "colour must not be null");
            this.colour = colour;
            return this;
        }
    }
}