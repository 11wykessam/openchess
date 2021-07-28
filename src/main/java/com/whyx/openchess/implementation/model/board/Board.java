package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardState;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class representing a board in chess.
 */
public class Board implements IBoard {

    private final IBoardState boardState;

    private Board(BoardBuilder builder) {
        this.boardState = builder.boardState;
    }

    /**
     * Get the state of the board.
     *
     * @return {@link IBoardState} object.
     */
    @Override
    public IBoardState getState() {
        return this.boardState;
    }

    /**
     * Get an instance of builder.
     *
     * @return {@link BoardBuilder} object.
     */
    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    /**
     * Builder class for {@link Board}.
     */
    static class BoardBuilder implements Builder<IBoard> {

        private IBoardState boardState;

        @Override
        public IBoard build() {
            requireNonNull(boardState, "boardState must not be null");
            return new Board(this);
        }

        public BoardBuilder withBoardState(IBoardState boardState) {
            requireNonNull(boardState, "boardState must not be null");
            this.boardState = boardState;
            return this;
        }


    }
}
