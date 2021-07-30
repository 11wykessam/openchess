package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.IBoardState;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.whyxcommons.collections.ImmutableList;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class that represents the state of a board in chess.
 */
public class BoardState implements IBoardState {

    private final ImmutableList<ImmutableList<ICell>> cells;

    private BoardState(BoardStateBuilder builder) {
        this.cells = builder.cells;
    }

    /**
     * Get the cells on the board.
     *
     * @return {@link ImmutableList} 2D immutable list of {@link ICell} objects.
     */
    @Override
    public ImmutableList<ImmutableList<ICell>> getCells() {
        return this.cells;
    }

    /**
     * Create an instance of the builder.
     *
     * @return {@link BoardStateBuilder} object.
     */
    public static BoardStateBuilder builder() {
        return new BoardStateBuilder();
    }

    /**
     * Builder responsible for building the {@link BoardState} class.
     */
    static class BoardStateBuilder implements Builder<IBoardState> {

        private ImmutableList<ImmutableList<ICell>> cells;

        @Override
        public IBoardState build() {
            requireNonNull(cells, "cells must not be null");
            return new BoardState(this);
        }

        public BoardStateBuilder withCells(ImmutableList<ImmutableList<ICell>> cells) {
            requireNonNull(cells, "cells must not be null");
            this.cells = cells;
            return this;
        }

    }

}
