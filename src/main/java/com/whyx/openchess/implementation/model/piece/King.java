package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;

/**
 * @author Sam Wykes.
 * Class representing a King in a game of chess.
 */
public class King extends TwoDimensionalChessPiece {

    @Override
    public IRuleBook<TwoDimensionalLocation> getRuleBook() {
        return null;
    }

    @Override
    public IPieceTeam getTeam() {
        return null;
    }
}
