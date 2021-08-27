package com.whyx.openchess.interfaces.model.rules;

import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;

/**
 * @param <T> The type of location the piece and board are related to.
 * @author Sam Wykes.
 * Interface representing a rule that a piece on a board must conform to.
 */
public interface IPieceRule<T extends ILocation> {

    /**
     * Checks whether given piece conforms to the rule.
     * Assumes that the piece is on the board.
     *
     * @param piece The {@link IPiece} that the rule ois being checked on.
     * @param game  The {@link IGame} the piece is in.
     * @return boolean.
     */
    boolean pieceConformsToRule(IPiece<T> piece, IGame<T> game);

}
