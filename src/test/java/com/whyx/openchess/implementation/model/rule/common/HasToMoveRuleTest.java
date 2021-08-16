package com.whyx.openchess.implementation.model.rule.common;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
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
 * Class used to test the {@link HasToMoveRule} class.
 */
@ExtendWith(MockitoExtension.class)
public class HasToMoveRuleTest {

    @Nested
    class Functionality {

        private HasToMoveRule<ILocation> rule;

        @BeforeEach
        void setup() {
            rule = new HasToMoveRule<>();
        }

        @Test
        void startAndEndNotEqualTest(
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IMove<ILocation> move,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            given(move.getStart()).willReturn(start);
            given(move.getDestination()).willReturn(destination);

            assertThat(rule.moveConformsToRule(move, piece, board)).isTrue();
        }

        @Test
        void startAndDestinationEqualTest(
                @Mock final ICell<ILocation> cell,
                @Mock final IMove<ILocation> move,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            given(move.getStart()).willReturn(cell);
            given(move.getDestination()).willReturn(cell);

            assertThat(rule.moveConformsToRule(move, piece, board)).isFalse();
        }

    }

}
