package com.whyx.openchess.interfaces.model.piece;

/**
 * @author Sam Wykes.
 * Interface representing a piece in a board game.
 */
public interface IPiece {

    /**
     * Get the rule book associated with the piece.
     *
     * @return {@link IPieceRuleBook} object.
     */
    IPieceRuleBook getRuleBook();

    /**
     * Get the team associated with the given piece.
     *
     * @return {@link IPieceTeam} object.
     */
    IPieceTeam getTeam();

}
