package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPieceState;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class used to represent the state of a piece in a game of chess.
 */
public class PieceState implements IPieceState {

    private final ICell cell;

    private PieceState(PieceStateBuilder builder) {
        this.cell = builder.cell;
    }

    /**
     * Getter for cell.
     *
     * @return {@link Optional} which may contain {@link ICell} object.
     */
    @Override
    public Optional<ICell> getCell() {
        return Optional.ofNullable(cell);
    }

    public static PieceStateBuilder builder() {
        return new PieceStateBuilder();
    }

    /**
     * Class responsible for building {@link PieceState} objects.
     */
    static class PieceStateBuilder implements Builder<IPieceState> {

        private ICell cell;

        @Override
        public IPieceState build() {
            return new PieceState(this);
        }

        public PieceStateBuilder withCell(ICell cell) {
            requireNonNull(cell, "cell must not be null");
            this.cell = cell;
            return this;
        }
    }

}
