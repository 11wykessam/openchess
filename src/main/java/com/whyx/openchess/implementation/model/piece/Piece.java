package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.PieceColour;

/**
 * @author Sam Wykes.
 * Class representing a piece in chess.
 */
public abstract class Piece implements IPiece {

    private final PieceColour colour;

    private Piece(PieceBuilder builder) {
        this.colour = builder.colour;
    }

    /**
     * Get the colour of the piece.
     *
     * @return {@link PieceColour} enum.
     */
    @Override
    public PieceColour getColour() {
        return this.colour;
    }

    public static abstract class PieceBuilder implements Builder<IPiece> {

        private PieceColour colour;

        @Override
        public IPiece build() {
            return null;
        }


        public PieceBuilder withColour(PieceColour colour) {
            this.colour = colour;
            return this;
        }
    }

}
