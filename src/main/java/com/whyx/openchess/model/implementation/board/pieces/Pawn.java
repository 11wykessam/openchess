package com.whyx.openchess.model.implementation.board.pieces;

import com.whyx.openchess.model.interfaces.board.IBoard;
import com.whyx.openchess.model.interfaces.board.pieces.IPawn;

import static java.util.Objects.requireNonNull;

public class Pawn extends Piece implements IPawn {

    Pawn(PawnBuilder builder) {
        super(builder);
    }

    @Override
    public boolean isLegalMove(int x, int y, IBoard currentBoard) {
        // preconditions.
        requireNonNull(currentBoard, "currentBoard must not be null");

        return false;
    }

    /**
     * Get builder for this class.
     * @return {@link PawnBuilder} object.
     */
    public static PawnBuilder builder() {
        return new PawnBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class used to build {@link Pawn} objects.
     */
    public static class PawnBuilder extends PieceBuilder<Pawn> {
        @Override
        public Pawn build() {
            requireNonNull(x, "x must not be null");
            requireNonNull(y, "y must not be null");
            requireNonNull(team, "team must not be null");
            return new Pawn(this);
        }
    }

}
