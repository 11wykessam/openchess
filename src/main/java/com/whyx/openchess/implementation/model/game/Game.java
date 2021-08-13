package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;

import static java.util.Objects.requireNonNull;

/**
 * @param <T> The type of location being used in the game.
 * @author Sam Wykes.
 * Class used to represent a board game.
 */
public class Game<T extends ILocation> implements IGame<T> {

    private final IBoard<T> board;

    /**
     * @param builder {@link GameBuilder} being used to construct the object.
     */
    private Game(final GameBuilder<T> builder) {
        this.board = builder.board;
    }

    /**
     * Get the board being played on.
     *
     * @return {@link IBoard} object.
     */
    @Override
    public IBoard<T> getBoard() {
        return this.board;
    }

    /**
     * Check whether a given move is legal.
     *
     * @param piece The {@link IPiece} being moved.
     * @param move  The {@link IMove} being made.
     * @return boolean.
     * @throws PieceNotFoundException thrown if the piece is not in the game.
     * @throws CellNotFoundException  thrown if either the start or destination cell not in the game.
     */
    @Override
    public boolean isMoveLegal(final IPiece<T> piece, final IMove<T> move) throws PieceNotFoundException, CellNotFoundException {
        requireNonNull(piece, "piece must not be null");
        requireNonNull(move, "move must not be null");

        // check both moves are on the board.
        if (!moveOnBoard(move)) throw new CellNotFoundException();

        // check the piece is on the start.
        if (!pieceOnMoveStart(piece, move)) throw new PieceNotFoundException();

        return piece.getRuleBook().getRules()
                .allMatch(rule -> rule.moveConformsToRule(move, piece, this.board));
    }

    /**
     * Checks whether the start and end destination of a move are both on the game's board.
     *
     * @param move {@link IMove} object.
     * @return boolean.
     */
    private boolean moveOnBoard(final IMove<T> move) {
        return board.containsCell(move.getStart()) && board.containsCell(move.getDestination());
    }

    /**
     * Checks whether a given piece is on the start cell of a move.
     *
     * @param piece {@link IPiece} object.
     * @param move  {@link IMove} object.
     * @return boolean.
     */
    private boolean pieceOnMoveStart(final IPiece<T> piece, final IMove<T> move) {
        return !(move.getStart().getPiece().isPresent() && move.getStart().getPiece().get().equals(piece));
    }

    /**
     * Create a builder instance.
     *
     * @return {@link GameBuilder} object.
     */
    public static <U extends ILocation> GameBuilder<U> builder() {
        return new GameBuilder<>();
    }

    /**
     * @param <U> The type of location being used in the game being made.
     * @author Sam Wykes.
     * Class used to create instances of the {@link Game} class.
     */
    public static class GameBuilder<U extends ILocation> {

        private IBoard<U> board;

        public GameBuilder<U> withBoard(final IBoard<U> board) {
            requireNonNull(board, "board must not be null");
            this.board = board;
            return this;
        }

        public Game<U> build() {
            requireNonNull(board, "board must not be null");
            return new Game<>(this);
        }

    }
}
