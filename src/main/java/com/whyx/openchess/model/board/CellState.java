package com.whyx.openchess.model.board;

import com.whyx.openchessinterface.model.board.ICellState;
import com.whyx.openchessinterface.model.piece.IPiece;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class representing the state of a cell in chess.
 */
public class CellState implements ICellState {

    private final IPiece piece;

    public CellState(CellStateBuilder cellStateBuilder) {
        this.piece = cellStateBuilder.piece;
    }

    /**
     * Getter for piece.
     *
     * @return {@link Optional} that may contain {@link IPiece} object.
     */
    @Override
    public Optional<IPiece> getPiece() {
        return Optional.ofNullable(this.piece);
    }

    /**
     * Return a builder object.
     *
     * @return {@link CellStateBuilder} object.
     */
    static CellStateBuilder builder() {
        return new CellStateBuilder();
    }

    /**
     * @author Sam Wykes.
     * Builder class for {@link CellState}.
     */
    static class CellStateBuilder {

        private IPiece piece;

        public CellState build() {
            return new CellState(this);
        }

        public CellStateBuilder withPiece(IPiece piece) {
            requireNonNull(piece, "piece must not be null");
            this.piece = piece;
            return this;
        }

    }
}
