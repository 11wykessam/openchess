package com.whyx.openchess.implementation.model.rule.piecerule;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

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
                @Mock final IBoard<ILocation> board,
                @Mock final ICell<ILocation> pieceCell,
                @Mock final IPiece<ILocation> piece,
                @Mock final IRuleBook<ILocation> pieceRuleBook,
                @Mock final ICell<ILocation> opponentCell,
                @Mock final IPiece<ILocation> opponent,
                @Mock final IRuleBook<ILocation> opponentRuleBook,
                @Mock final IMoveRule<ILocation> opponentRule
        ) {
            given(game.getBoard()).willReturn(board);
            given(board.containsCell(pieceCell)).willReturn(true);
            given(board.containsCell(opponentCell)).willReturn(true);
            given(board.getCells()).will(invocationOnMock -> Stream.of(pieceCell, opponentCell));

            given(pieceCell.getPiece()).willReturn(Optional.of(piece));
            given(piece.getRuleBook()).willReturn(pieceRuleBook);
            given(pieceRuleBook.getRules()).will(invocationOnMock -> Stream.of());

            given(opponentCell.getPiece()).willReturn(Optional.of(opponent));
            given(opponent.getRuleBook()).willReturn(opponentRuleBook);
            given(opponentRuleBook.getRules()).will(invocationOnMock -> Stream.of(opponentRule));

            given(opponentRule.moveConformsToRule(opponentCell, pieceCell, opponent, board)).willReturn(true);

            assertThat(rule.pieceConformsToRule(piece, game)).isTrue();
        }

    }

}