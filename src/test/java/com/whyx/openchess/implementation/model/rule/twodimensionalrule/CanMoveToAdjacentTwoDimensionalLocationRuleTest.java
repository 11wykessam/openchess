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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author Sam Wykes.
 * Class used to test the {@link CanMoveToAdjacentTwoDimensionalLocationRule}.
 */
@ExtendWith(MockitoExtension.class)
class CanMoveToAdjacentTwoDimensionalLocationRuleTest {

    @Nested
    class Functionality {

        @Mock
        private TwoDimensionalLocation startLocation;
        @Mock
        private TwoDimensionalLocation destinationLocation;
        @Mock
        private ICell<TwoDimensionalLocation> start;
        @Mock
        private ICell<TwoDimensionalLocation> destination;
        @Mock
        private IMove<TwoDimensionalLocation> move;
        @Mock
        private IPiece<TwoDimensionalLocation> piece;
        @Mock
        private IBoard<TwoDimensionalLocation> board;

        private static final int START_X = 5;
        private static final int START_Y = 6;

        private CanMoveToAdjacentTwoDimensionalLocationRule rule;

        @BeforeEach
        void setup() {
            rule = new CanMoveToAdjacentTwoDimensionalLocationRule();
        }

        @ParameterizedTest
        @MethodSource("com.whyx.openchess.implementation.model.rule.twodimensionalrule.CanMoveToAdjacentTwoDimensionalLocationRuleTest#providePossibleMovementVectors")
        void canMoveOneOrLessSquares(final int xOffset, final int yOffset) {
            given(move.getStart()).willReturn(start);
            given(move.getDestination()).willReturn(destination);
            given(start.getLocation()).willReturn(startLocation);
            given(destination.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(START_X);
            given(startLocation.getY()).willReturn(START_Y);
            given(destinationLocation.getX()).willReturn(START_X + xOffset);
            given(destinationLocation.getY()).willReturn(START_Y + yOffset);

            assertThat(rule.moveConformsToRule(move, piece, board)).isTrue();
        }

        @Test
        void cannotMoveMoreThanOneSquare() {
            given(move.getStart()).willReturn(start);
            given(move.getDestination()).willReturn(destination);
            given(start.getLocation()).willReturn(startLocation);
            given(destination.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(START_X);
            given(destinationLocation.getX()).willReturn(START_X + 2);

            assertThat(rule.moveConformsToRule(move, piece, board)).isFalse();
        }

    }

    private static Stream<Arguments> providePossibleMovementVectors() {
        return Stream.of(
                Arguments.of(-1, -1),
                Arguments.of(-1, 0),
                Arguments.of(-1, 1),
                Arguments.of(0, -1),
                Arguments.of(0, 0),
                Arguments.of(0, 1),
                Arguments.of(1, -1),
                Arguments.of(1, 0),
                Arguments.of(1, 1)
        );
    }

}