package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;

/**
 * @author Sam Wykes.
 * Class representing a King in a game of chess.
 */
public class King implements IPiece {

    @Override
    public IRuleBook getRuleBook() {
        return null;
    }

    @Override
    public IPieceTeam getTeam() {
        return null;
    }
}
