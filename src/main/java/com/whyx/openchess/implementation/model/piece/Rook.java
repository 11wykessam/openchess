package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class represents a rook in a game of chess.
 */
public class Rook extends Piece {

    private Rook(RookBuilder builder) {
        super(builder);
    }

    /**
     * Generate instance of builder.
     *
     * @return {@link RookBuilder} object.
     */
    public static RookBuilder builder() {
        return new RookBuilder();
    }

    @Override
    public boolean isMoveLegal(IBoard board, ICell start, ICell end) {
        return false;
    }

    /**
     * Class responsible for building {@link Rook} instances.
     */
    static class RookBuilder extends PieceBuilder {
        @Override
        public IPiece build() {
            requireNonNull(this.state, "state must not be null");
            requireNonNull(this.colour, "colour must not be null");
            return new Rook(this);
        }
    }


}
