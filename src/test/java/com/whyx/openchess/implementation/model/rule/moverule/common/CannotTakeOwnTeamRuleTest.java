package com.whyx.openchess.implementation.model.rule.moverule.common;

import com.whyx.openchess.implementation.model.rule.moverule.MoveRuleTest;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Sam Wykes.
 * Class used to test the {@link CannotTakeOwnTeamRule} class.
 */
@ExtendWith(MockitoExtension.class)
class CannotTakeOwnTeamRuleTest extends MoveRuleTest<ILocation> {

    @Override
    protected IMoveRule<ILocation> createRule() {
        return new CannotTakeOwnTeamRule<>();
    }

    @Nested
    class Functionality {

        private CannotTakeOwnTeamRule<ILocation> rule;

        @BeforeEach
        void setup() {
            rule = new CannotTakeOwnTeamRule<>();
        }

        @Test
        void pieceCannotTakeOwnTeamTest(
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> destinationPiece,
                @Mock final IPieceTeam team,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            given(destination.getPiece()).willReturn(Optional.ofNullable(destinationPiece));
            given(destinationPiece.getTeam()).willReturn(team);
            given(piece.getTeam()).willReturn(team);

            assertThat(rule.moveConformsToRule(start, destination, piece, board)).isFalse();
        }

        @Test
        void pieceCanTakeAnotherTeamTest(
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> destinationPiece,
                @Mock final IPieceTeam team,
                @Mock final IPieceTeam destinationTeam,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            given(destination.getPiece()).willReturn(Optional.of(destinationPiece));
            given(destinationPiece.getTeam()).willReturn(destinationTeam);
            given(piece.getTeam()).willReturn(team);

            assertThat(rule.moveConformsToRule(start, destination, piece, board)).isTrue();
        }

        @Test
        void pieceCanTakeEmptyCellTest(
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            given(destination.getPiece()).willReturn(Optional.empty());

            assertThat(rule.moveConformsToRule(start, destination, piece, board)).isTrue();
        }

    }

}