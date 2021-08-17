package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class representing a piece in 2D chess.
 */
public abstract class TwoDimensionalChessPiece extends Piece<TwoDimensionalLocation> {

    /**
     * @param <T>
     * @author Sam Wykes.
     * Class used to create instances of the {@link TwoDimensionalChessPiece} class.
     */
    public abstract static class TwoDimensionalChessPieceBuilder<T extends TwoDimensionalChessPiece> {

        protected IPieceTeam pieceTeam;

        public TwoDimensionalChessPieceBuilder<T> withPieceTeam(final IPieceTeam pieceTeam) {
            requireNonNull(pieceTeam, "pieceTeam must not be null");
            this.pieceTeam = pieceTeam;
            return this;
        }

        public abstract T build();

    }

}
