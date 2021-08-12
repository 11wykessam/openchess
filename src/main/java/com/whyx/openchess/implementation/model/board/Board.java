package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.rules.IMove;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class represents a board in a board game.
 */
public class Board implements IBoard {

    private final Set<ICell> cells;

    private Board(BoardBuilder boardBuilder) {
        this.cells = boardBuilder.cells;
    }

    @Override
    public Stream<ICell> getCells() {
        return cells.stream();
    }

    @Override
    public boolean isMoveObstructed(IMove move) {
        return false;
    }

    @Override
    public IBoard placeOnCell(ICell cell, IPiece piece) throws CellNotFoundException {
        boolean cellFound = false;
        return null;
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

        public BoardBuilder withCells(Set<ICell> cells) {
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
