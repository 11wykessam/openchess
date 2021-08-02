package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.whyxcommons.collections.ImmutableList;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class that represents a board in a game of chess.
 */
public class Board implements IBoard {

    private final ImmutableList<ImmutableList<ICell>> cells;

    public Board(BoardBuilder boardBuilder) {
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
