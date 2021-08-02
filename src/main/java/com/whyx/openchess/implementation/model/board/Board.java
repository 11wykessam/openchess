package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.whyxcommons.collections.ImmutableList;

public class Board implements IBoard {

    @Override
    public ImmutableList<ImmutableList<ICell>> getCells() {
        return null;
    }

    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    public static class BoardBuilder implements Builder<IBoard> {

        @Override
        public IBoard build() {
            return null;
        }

        public BoardBuilder withCells(ImmutableList<ImmutableList<ICell>> cells) {
            return null;
        }
    }
}
