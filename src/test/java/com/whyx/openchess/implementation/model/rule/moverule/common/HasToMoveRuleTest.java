package com.whyx.openchess.implementation.model.rule.moverule.common;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sam Wykes.
 * Class used to test the {@link HasToMoveRule} class.
 */
@ExtendWith(MockitoExtension.class)
class HasToMoveRuleTest {

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
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            assertThat(rule.moveConformsToRule(start, destination, piece, board)).isTrue();
        }

        @Test
        void startAndDestinationEqualTest(
                @Mock final ICell<ILocation> cell,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            assertThat(rule.moveConformsToRule(cell, cell, piece, board)).isFalse();
        }

    }

}
