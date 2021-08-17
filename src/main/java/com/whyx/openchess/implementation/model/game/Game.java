package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.exceptions.IllegalMoveException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.game.IGameState;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;

import java.util.Set;

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
     * Checks if a given move is legal.
     *
     * @param piece       The {@link IPiece} being moved.
     * @param start       The {@link ICell} the move is being made from.
     * @param destination The {@link ICell} the move is being made to.
     * @return boolean.
     * @throws PieceNotFoundException Thrown if the piece is not on the start cell.
     * @throws CellNotFoundException  Thrown if either the start or destination cell is not on the board.
     */
    @Override
    public boolean isMoveLegal(final IPiece<T> piece, final ICell<T> start, final ICell<T> destination) throws PieceNotFoundException, CellNotFoundException {
        requireNonNull(piece, "piece must not be null");
        requireNonNull(start, "start must not be null");
        requireNonNull(destination, "destination must not be null");

        // check both cells are on the board.
        if (!board.containsCell(start) || !board.containsCell(destination)) throw new CellNotFoundException();

        // check the piece is on the start.
        if (start.getPiece().isEmpty() || !start.getPiece().get().equals(piece)) throw new PieceNotFoundException();

        return piece.getRuleBook().getRules()
                .allMatch(rule -> rule.moveConformsToRule(start, destination, piece, this.board));
    }

    @Override
    public IGame<T> makeMove(final IPiece<T> piece, final IMove<T> move) throws IllegalMoveException {
        return null;
    }

    /**
     * Get the possible moves originating from a given cell.
     *
     * @param start The {@link ICell} moves are being made from.
     * @return {@link Set} containing {@link IMove} objects.
     * @throws CellNotFoundException Thrown if cell is not on the game's board.
     */
    @Override
    public Set<IMove<T>> getPossibleMovesFromCell(final ICell<T> start) throws CellNotFoundException {

        // check if cell has a piece.
        if (start.getPiece().isEmpty()) return Set.of();

        final IPiece<T> piece = start.getPiece().get();
        return null;
    }

    @Override
    public IGameState getState() {
        return null;
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
