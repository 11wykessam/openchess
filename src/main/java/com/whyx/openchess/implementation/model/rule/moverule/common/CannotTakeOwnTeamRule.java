package com.whyx.openchess.implementation.model.rule.moverule.common;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;

/**
 * @author Sam Wykes.
 * Class representing a rule specifying that pieces are not able to take pieces of their own colour.
 */
public class CannotTakeOwnTeamRule<T extends ILocation> implements IMoveRule<T> {
    @Override
    public boolean moveConformsToRule(final ICell<T> start, final ICell<T> destination, final IPiece<T> piece, final IBoard<T> board) {
        return !(destination.getPiece().isPresent()
                && destination.getPiece().get().getTeam().equals(piece.getTeam()));
    }
}
