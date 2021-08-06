package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.implementation.exceptions.CellOutOfBoundsException;
import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.whyxcommons.collections.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class that represents a board in a game of chess.
 */
public class Board implements IBoard {

    private final ImmutableList<ImmutableList<ICell>> cells;

    private Board(BoardBuilder boardBuilder) {
        this.cells = boardBuilder.cells;
    }

    /**
     * Get the cells in the board.
     *
     * @return 2D {@link ImmutableList} containing the cells in the board.
     */
    @Override
    public ImmutableList<ImmutableList<ICell>> getCells() {
        return this.cells;
    }

    /**
     * Get the width of the board.
     *
     * @return int.
     */
    @Override
    public int getWidth() {
        return cells.size();
    }

    /**
     * Get the height of the board.
     *
     * @return int.
     */
    @Override
    public int getHeight() {
        return cells.get(0).size();
    }

    /**
     * Place a piece on the board.
     *
     * @param x     x coordinate of cell the piece is being placed on.
     * @param y     y coordinate of cell the piece is being placed on.
     * @param piece {@link IPiece} being placed.
     * @return newly created {@link IBoard} object.
     */
    @Override
    public IBoard placePiece(final int x, final int y, final IPiece piece) {
        // do a check on the coordinates.
        if (x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight()) throw new CellOutOfBoundsException();

        // copy the cell list to mutable version.
        List<ImmutableList<ICell>> mutableColumns = cells.mutableCopy();
        List<List<ICell>> mutableCells = mutableColumns.stream()
                .map(ImmutableList::mutableCopy)
                .collect(Collectors.toList());

        // make the change to the mutable version,
        ICell cell = Cell.builder()
                .withX(() -> x)
                .withY(() -> y)
                .withPiece(piece)
                .build();
        mutableCells.get(x).add(y, cell);

        // convert back to immutable version.
        ImmutableList<ImmutableList<ICell>> immutableList = ImmutableList.ofList(
                mutableCells.stream().map(ImmutableList::ofList)
                        .collect(Collectors.toList())
        );

        return Board.builder()
                .withCells(immutableList)
                .build();
    }

    /**
     * Generate an instance of a builder.
     *
     * @return {@link BoardBuilder} object.
     */
    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    /**
     * @author Sam Wykes.
     * Builder class responsible for creating {@link Board} objects.
     */
    public static class BoardBuilder implements Builder<IBoard> {

        private ImmutableList<ImmutableList<ICell>> cells;

        /**
         * Build the instance.
         *
         * @return {@link IBoard} object.
         */
        @Override
        public IBoard build() {
            requireNonNull(cells, "cells must not be null");
            return new Board(this);
        }

        /**
         * Provide the builder cells for the board.
         *
         * @param cells 2D {@link ImmutableList}.
         * @return {@link BoardBuilder} object.
         */
        public BoardBuilder withCells(ImmutableList<ImmutableList<ICell>> cells) {
            requireNonNull(cells, "cells must not be null");
            this.cells = cells;
            return this;
        }
    }
}
