package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.rules.IMove;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class represents a board in a board game.
 */
public class Board implements IBoard {

    private final Set<ICell> cells;

    private Board(final BoardBuilder boardBuilder) {
        this.cells = boardBuilder.cells;
    }

    @Override
    public Stream<ICell> getCells() {
        return cells.stream();
    }

    @Override
    public boolean isMoveObstructed(final IMove move) {
        return false;
    }

    @Override
    public IBoard placeOnCell(final ICell targetCell, final IPiece piece) throws CellNotFoundException {
        requireNonNull(targetCell, "targetCell must not be null");
        requireNonNull(piece, "piece must not be null");

        // change the cell that needs the piece placed on it.
        final Set<ICell> alteredCells = this.cells.stream()
                .map(changeCellPiece(targetCell, piece)).collect(Collectors.toSet());

        // check if anything has been changed.
        if (alteredCells.equals(this.cells)) throw new CellNotFoundException();

        // construct the new board.
        return Board.builder()
                .withCells(alteredCells)
                .build();
    }

    private Function<ICell, ICell> changeCellPiece(final ICell targetCell, final IPiece piece) {
        return cell -> cell.equals(targetCell) ?
                Cell.builder()
                        .withLocation(cell.getLocation())
                        .withPiece(piece)
                        .build() :
                cell;
    }

    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class used to create instances of {@link Board}.
     */
    public static class BoardBuilder {

        public Set<ICell> cells;

        public BoardBuilder withCells(final Set<ICell> cells) {
            requireNonNull(cells, "cells must not be null");
            this.cells = cells;
            return this;
        }

        public IBoard build() {
            requireNonNull(cells, "cells must not be null");
            return new Board(this);
        }
    }
}
