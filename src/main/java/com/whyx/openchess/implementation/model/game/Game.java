package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.rules.IMove;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class used to represent a board game.
 */
public class Game implements IGame {

    private final IBoard board;

    /**
     * @param builder {@link GameBuilder} being used to construct the object.
     */
    private Game(final GameBuilder builder) {
        this.board = builder.board;
    }

    /**
     * Get the board being played on.
     *
     * @return {@link IBoard} object.
     */
    @Override
    public IBoard getBoard() {
        return this.board;
    }

    /**
     * Check whether a given move is legal.
     *
     * @param piece The {@link IPiece} being moved.
     * @param move  The {@link IMove} being made.
     * @return boolean.
     */
    @Override
    public boolean isMoveLegal(final IPiece piece, final IMove move) {
        return false;
    }

    /**
     * Create a builder instance.
     *
     * @return {@link GameBuilder} object.
     */
    public static GameBuilder builder() {
        return new GameBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class used to create instances of the {@link Game} class.
     */
    public static class GameBuilder {

        private IBoard board;

        public GameBuilder withBoard(final IBoard board) {
            requireNonNull(board, "board must not be null");
            this.board = board;
            return this;
        }

        public Game build() {
            requireNonNull(board, "board must not be null");
            return new Game(this);
        }

    }
}
