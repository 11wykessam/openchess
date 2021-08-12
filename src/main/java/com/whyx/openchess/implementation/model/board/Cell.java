package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class that represents a cell in a board game.
 */
public class Cell implements ICell {

    private final IPiece piece;

    public Cell(CellBuilder cellBuilder) {
        this.piece = cellBuilder.piece;
    }

    @Override
    public Optional<IPiece> getPiece() {
        return Optional.ofNullable(piece);
    }

    public static CellBuilder builder() {
        return new CellBuilder();
    }

    public static class CellBuilder {

        private IPiece piece;

        public CellBuilder withPiece(IPiece piece) {
            requireNonNull(piece, "piece is not null");
            this.piece = piece;
            return this;
        }

        public ICell build() {
            return new Cell(this);
        }
    }
}
