package com.whyx.openchess.implementation.model.rule.piecerule;

import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import com.whyx.openchess.interfaces.model.rules.IPieceRule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @param <T> The type of location being played on.
 * @author Sam Wykes.
 * Class representing the rule that a piece can be taken in the next move.
 */
public class CheckRule<T extends ILocation> implements IPieceRule<T> {
    @Override
    public boolean pieceConformsToRule(final IPiece<T> piece, final IGame<T> game) {
        // get the stream of available moves.
        return game.getPossibleMoves().stream()
                .anyMatch(filterMovesThatEndOnPiece(piece));
    }

    /**
     * Function that filters moves that's destination contains a given piece.
     *
     * @param piece The {@link IPiece} in question.
     * @return {@link Function} object.
     */
    private Predicate<IMove<T>> filterMovesThatEndOnPiece(final IPiece<T> piece) {
        return move ->
                move.getDestination().getPiece().isPresent() && move.getDestination().getPiece().get().equals(piece);
    }
}
