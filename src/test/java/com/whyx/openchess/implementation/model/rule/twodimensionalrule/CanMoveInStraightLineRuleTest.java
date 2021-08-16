package com.whyx.openchess.implementation.model.rule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Sam Wykes.
 * Class used to test the {@link CanMoveInStraightLineRule} class.
 */
@ExtendWith(MockitoExtension.class)
class CanMoveInStraightLineRuleTest {

    @Nested
    class Functionality {

        private final int DEFAULT_X = 5;
        private final int DEFAULT_Y = 6;

        private CanMoveInStraightLineRule rule;

        @BeforeEach
        void setup() {
            rule = new CanMoveInStraightLineRule();
        }

        @Test
        void canMoveInVerticalLineTest(
                @Mock final IMove<TwoDimensionalLocation> move,
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(move.getStart()).willReturn(startCell);
            given(move.getDestination()).willReturn(destinationCell);
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(DEFAULT_X);
            given(destinationLocation.getX()).willReturn(DEFAULT_X);

            assertThat(rule.moveConformsToRule(move, piece, board)).isTrue();
        }

        @Test
        void canMoveInHorizontalLineTest(
                @Mock final IMove<TwoDimensionalLocation> move,
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(move.getStart()).willReturn(startCell);
            given(move.getDestination()).willReturn(destinationCell);
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(DEFAULT_X);
            given(destinationLocation.getX()).willReturn(DEFAULT_X + 6);
            given(startLocation.getY()).willReturn(DEFAULT_Y);
            given(destinationLocation.getY()).willReturn(DEFAULT_Y);

            assertThat(rule.moveConformsToRule(move, piece, board)).isTrue();
        }
    }

}