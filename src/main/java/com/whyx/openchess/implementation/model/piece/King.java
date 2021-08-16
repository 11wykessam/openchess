package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.implementation.model.rule.RuleBook;
import com.whyx.openchess.implementation.model.rule.common.HasToMoveRule;
import com.whyx.openchess.implementation.model.rule.twodimensionalrule.CanMoveToAdjacentTwoDimensionalLocationRule;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import com.whyx.openchess.interfaces.model.rules.IRule;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;

import java.util.Set;

/**
 * @author Sam Wykes.
 * Class representing a King in a game of chess.
 */
public class King extends TwoDimensionalChessPiece {

    /**
     * Get the rule book associated with this piece.
     *
     * @return {@link IRuleBook} object.
     */
    @Override
    public IRuleBook<TwoDimensionalLocation> getRuleBook() {

        final Set<IRule<TwoDimensionalLocation>> ruleSet = Set.of(
                new HasToMoveRule<>(),
                new CanMoveToAdjacentTwoDimensionalLocationRule()
        );

        return RuleBook.<TwoDimensionalLocation>builder()
                .withRules(ruleSet)
                .build();

    }

    @Override
    public IPieceTeam getTeam() {
        return null;
    }

    public static class KingBuilder {

    }
}
