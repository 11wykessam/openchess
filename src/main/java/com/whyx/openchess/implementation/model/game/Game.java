package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.interfaces.common.Builder;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.game.IGame;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class representing a game of chess.
 */
public class Game implements IGame {

    private final IBoard board;

    private Game(GameBuilder builder) {
        this.board = builder.board;
    }

    /**
     * Getter for board.
     *
     * @return {@link IBoard} object.
     */
    @Override
    public IBoard getBoard() {
        return this.board;
    }

    /**
     * Create an instance of {@link GameBuilder}.
     *
     * @return {@link GameBuilder} object.
     */
    public static GameBuilder builder() {
        return new GameBuilder();
    }

    /**
     * Class responsible for building {@link Game} instances.
     */
    static class GameBuilder implements Builder<IGame> {

        private IBoard board;

        @Override
        public IGame build() {
            requireNonNull(board, "board must not be null");
            return new Game(this);
        }

        public GameBuilder withBoard(IBoard board) {
            requireNonNull(board, "board must not be null");
            this.board = board;
            return this;
        }
    }
}
