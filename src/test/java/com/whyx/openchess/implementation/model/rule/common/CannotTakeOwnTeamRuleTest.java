package com.whyx.openchess.implementation.model.rule.common;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import com.whyx.openchess.interfaces.model.rules.IMove;
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
class CannotTakeOwnTeamRuleTest {

    @Nested
    class Functionality {

        private CannotTakeOwnTeamRule<ILocation> rule;

        @BeforeEach
        void setup() {
            rule = new CannotTakeOwnTeamRule<>();
        }

        @Test
        void pieceCannotTakeOwnTeamTest(
                @Mock final IMove<ILocation> move,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> destinationPiece,
                @Mock final IPieceTeam team,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            given(move.getDestination()).willReturn(destination);
            given(destination.getPiece()).willReturn(Optional.ofNullable(destinationPiece));
            given(destinationPiece.getTeam()).willReturn(team);
            given(piece.getTeam()).willReturn(team);

            assertThat(rule.moveConformsToRule(move, piece, board)).isFalse();
        }

        @Test
        void pieceCanTakeAnotherTeamTest(
                @Mock final IMove<ILocation> move,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> destinationPiece,
                @Mock final IPieceTeam team,
                @Mock final IPieceTeam destinationTeam,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            given(move.getDestination()).willReturn(destination);
            given(destination.getPiece()).willReturn(Optional.ofNullable(destinationPiece));
            given(destinationPiece.getTeam()).willReturn(destinationTeam);
            given(piece.getTeam()).willReturn(team);

            assertThat(rule.moveConformsToRule(move, piece, board)).isFalse();
        }

    }

}