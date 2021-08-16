package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.implementation.model.rule.RuleBook;
import com.whyx.openchess.implementation.model.rule.common.HasToMoveRule;
import com.whyx.openchess.implementation.model.rule.twodimensionalrule.CanMoveToAdjacentTwoDimensionalLocationRule;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import com.whyx.openchess.interfaces.model.rules.IRule;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;

import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Class representing a King in a game of chess.
 */
public class King extends TwoDimensionalChessPiece {

    private final IPieceTeam pieceTeam;

    public King(final KingBuilder builder) {
        this.pieceTeam = builder.pieceTeam;
    }

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

    /**
     * Get the team that the piece belongs to.
     *
     * @return {@link IPieceTeam} object.
     */
    @Override
    public IPieceTeam getTeam() {
        return this.pieceTeam;
    }

    /**
     * Get instance of builder.
     *
     * @return {@link KingBuilder} object.
     */
    public static KingBuilder builder() {
        return new KingBuilder();
    }

    /**
     * @author Sam Wykes.
     * Class used to construct {@link King} objects.
     */
    public static class KingBuilder {

        private IPieceTeam pieceTeam;

        public KingBuilder withPieceTeam(final IPieceTeam pieceTeam) {
            requireNonNull(pieceTeam, "pieceTeam must not be null");
            this.pieceTeam = pieceTeam;
            return this;
        }

        public King build() {
            requireNonNull(pieceTeam, "pieceTeam must not be null");
            return new King(this);
        }
    }
}
