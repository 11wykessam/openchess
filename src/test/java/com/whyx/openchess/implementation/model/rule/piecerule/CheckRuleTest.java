package com.whyx.openchess.implementation.model.rule.piecerule;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Sam Wykes.
 * Class used to test the {@link CheckRule} class.
 */
@ExtendWith(MockitoExtension.class)
class CheckRuleTest {

    @Nested
    class Functionality {

        private CheckRule<ILocation> rule;

        @BeforeEach
        void setup() {
            rule = new CheckRule<>();
        }

        @Test
        void checkRuleTrueIfPieceCanBeTakenTest(
                @Mock final IGame<ILocation> game,
                @Mock final IMove<ILocation> possibleMove,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> piece
        ) {
            given(game.getPossibleMoves()).willReturn(Set.of(possibleMove));
            given(possibleMove.getDestination()).willReturn(destination);
            given(destination.getPiece()).willReturn(Optional.of(piece));

            assertThat(rule.pieceConformsToRule(piece, game)).isTrue();
        }

        @Test
        void checkRuleFalseIfPieceCannotBeTakenTest(
                @Mock final IGame<ILocation> game,
                @Mock final IMove<ILocation> possibleMove,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> piece
        ) {
            given(game.getPossibleMoves()).willReturn(Set.of(possibleMove));
            given(possibleMove.getDestination()).willReturn(destination);
            given(destination.getPiece()).willReturn(Optional.empty());

            assertThat(rule.pieceConformsToRule(piece, game)).isFalse();
        }

    }

}