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
public class Piece implements IPiece {

    private final IPieceState state;
    private final PieceColour colour;

    public Piece(PieceBuilder builder) {
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
     * Get instance of builder.
     *
     * @return {@link PieceBuilder} object.
     */
    public static PieceBuilder builder() {
        return new PieceBuilder();
    }

    /**
     * Builder responsible for building {@link Piece} class.
     */
    static class PieceBuilder implements Builder<IPiece> {

        private IPieceState state;
        private PieceColour colour;

        @Override
        public IPiece build() {
            requireNonNull(state, "state must not be null");
            requireNonNull(colour, "colour must not be null");
            return new Piece(this);
        }

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