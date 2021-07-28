package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.common.PieceColour;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceState;

/**
 * @author Sam Wykes.
 * Class used to represent
 */
public class Piece implements IPiece {

    @Override
    public IPieceState getState() {
        return null;
    }

    @Override
    public PieceColour getColour() {
        return null;
    }

    public static PieceBuilder builder() {
        return new PieceBuilder();
    }

    static class PieceBuilder implements Builder<IPiece> {

        @Override
        public IPiece build() {
            return null;
        }

        public PieceBuilder withState(IPieceState state) {
            return this;
        }

        public PieceBuilder withColour(PieceColour colour) {
            return this;
        }
    }
}