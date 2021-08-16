package com.whyx.openchess.implementation.model.rule.common;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import com.whyx.openchess.interfaces.model.rules.IRule;

/**
 * @author Sam Wykes.
 * Class representing a rule specifying that pieces are not able to take pieces of their own colour.
 */
public class CannotTakeOwnTeamRule<T extends ILocation> implements IRule<T> {
    @Override
    public boolean moveConformsToRule(final IMove<T> move, final IPiece<T> piece, final IBoard<T> board) {
        return !(move.getDestination().getPiece().isPresent()
                && move.getDestination().getPiece().get().getTeam().equals(piece.getTeam()));
    }
}
