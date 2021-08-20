package com.whyx.openchess.implementation.model.rule.moverule.twodimensionalrule;

import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPiece;
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
 * Class used to test the {@link CanMoveInUnobstructedStraightLineRule}.
 */
@ExtendWith(MockitoExtension.class)
class CanMoveInUnobstructedStraightLineRuleTest {

    @Nested
    class Functionality {

        private final Integer DEFAULT_Y = 6;
        private final Integer DEFAULT_X = 5;

        private CanMoveInUnobstructedStraightLineRule rule;

        @BeforeEach
        void setup() {
            rule = new CanMoveInUnobstructedStraightLineRule();
        }

        @Test
        void verticalObstructionUpwardsMovementTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final ICell<TwoDimensionalLocation> obstructionCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final TwoDimensionalLocation obstructionLocation,
                @Mock final IPiece<TwoDimensionalLocation> obstruction,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(obstructionCell.getLocation()).willReturn(obstructionLocation);
            given(startLocation.getX()).willReturn(0);
            given(startLocation.getY()).willReturn(0);
            given(destinationLocation.getX()).willReturn(0);
            given(destinationLocation.getY()).willReturn(2);
            given(obstructionLocation.getX()).willReturn(0);
            given(obstructionLocation.getY()).willReturn(1);
            given(obstructionCell.getPiece()).willReturn(Optional.of(obstruction));
            given(board.getCells()).will(invocationOnMock -> Stream.of(startCell, destinationCell, obstructionCell));

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isFalse();
        }

        @Test
        void noVerticalObstructionUpwardsMovementTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final ICell<TwoDimensionalLocation> obstructionCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final TwoDimensionalLocation obstructionLocation,
                @Mock final IPiece<TwoDimensionalLocation> obstruction,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(obstructionCell.getLocation()).willReturn(obstructionLocation);
            given(startLocation.getX()).willReturn(0);
            given(startLocation.getY()).willReturn(0);
            given(destinationLocation.getX()).willReturn(0);
            given(destinationLocation.getY()).willReturn(2);
            given(obstructionLocation.getX()).willReturn(0);
            given(obstructionLocation.getY()).willReturn(1);
            given(obstructionCell.getPiece()).willReturn(Optional.empty());
            given(board.getCells()).will(invocationOnMock -> Stream.of(startCell, destinationCell, obstructionCell));

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isTrue();
        }

        @Test
        void missingCellVerticalMoveTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(0);
            given(startLocation.getY()).willReturn(0);
            given(destinationLocation.getX()).willReturn(0);
            given(destinationLocation.getY()).willReturn(2);
            given(board.getCells()).will(invocationOnMock -> Stream.of(startCell, destinationCell));

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isFalse();
        }

        @Test
        void horizontalObstructionUpwardsMovementTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final ICell<TwoDimensionalLocation> obstructionCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final TwoDimensionalLocation obstructionLocation,
                @Mock final IPiece<TwoDimensionalLocation> obstruction,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(obstructionCell.getLocation()).willReturn(obstructionLocation);
            given(startLocation.getX()).willReturn(0);
            given(startLocation.getY()).willReturn(0);
            given(destinationLocation.getX()).willReturn(2);
            given(destinationLocation.getY()).willReturn(0);
            given(obstructionLocation.getX()).willReturn(1);
            given(obstructionLocation.getY()).willReturn(0);
            given(obstructionCell.getPiece()).willReturn(Optional.of(obstruction));
            given(board.getCells()).will(invocationOnMock -> Stream.of(startCell, destinationCell, obstructionCell));

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isFalse();
        }

        @Test
        void noHorizontalObstructionUpwardsMovementTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final ICell<TwoDimensionalLocation> obstructionCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final TwoDimensionalLocation obstructionLocation,
                @Mock final IPiece<TwoDimensionalLocation> obstruction,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(obstructionCell.getLocation()).willReturn(obstructionLocation);
            given(startLocation.getX()).willReturn(0);
            given(startLocation.getY()).willReturn(0);
            given(destinationLocation.getX()).willReturn(2);
            given(destinationLocation.getY()).willReturn(0);
            given(obstructionLocation.getX()).willReturn(1);
            given(obstructionLocation.getY()).willReturn(0);
            given(obstructionCell.getPiece()).willReturn(Optional.empty());
            given(board.getCells()).will(invocationOnMock -> Stream.of(startCell, destinationCell, obstructionCell));

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isTrue();
        }

        @Test
        void missingCellHorizontalMoveTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(0);
            given(startLocation.getY()).willReturn(0);
            given(destinationLocation.getX()).willReturn(2);
            given(destinationLocation.getY()).willReturn(0);
            given(board.getCells()).will(invocationOnMock -> Stream.of(startCell, destinationCell));

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isFalse();
        }

        @Test
        void canMoveInVerticalLineTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(DEFAULT_X);
            given(startLocation.getY()).willReturn(DEFAULT_Y);
            given(destinationLocation.getX()).willReturn(DEFAULT_X);
            given(destinationLocation.getY()).willReturn(DEFAULT_Y + 1);

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isTrue();
        }

        @Test
        void canMoveInHorizontalLineTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(DEFAULT_X);
            given(destinationLocation.getX()).willReturn(DEFAULT_X + 1);
            given(startLocation.getY()).willReturn(DEFAULT_Y);
            given(destinationLocation.getY()).willReturn(DEFAULT_Y);

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isTrue();
        }

        @Test
        void cannotMoveIfNotInStraightLineTest(
                @Mock final ICell<TwoDimensionalLocation> startCell,
                @Mock final ICell<TwoDimensionalLocation> destinationCell,
                @Mock final TwoDimensionalLocation startLocation,
                @Mock final TwoDimensionalLocation destinationLocation,
                @Mock final IPiece<TwoDimensionalLocation> piece,
                @Mock final IBoard<TwoDimensionalLocation> board
        ) {
            given(startCell.getLocation()).willReturn(startLocation);
            given(destinationCell.getLocation()).willReturn(destinationLocation);
            given(startLocation.getX()).willReturn(DEFAULT_X);
            given(destinationLocation.getX()).willReturn(DEFAULT_X + 1);
            given(startLocation.getY()).willReturn(DEFAULT_Y);
            given(destinationLocation.getY()).willReturn(DEFAULT_Y + 1);

            assertThat(rule.moveConformsToRule(startCell, destinationCell, piece, board)).isFalse();
        }

    }

}