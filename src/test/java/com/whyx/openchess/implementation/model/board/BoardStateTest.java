package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.IBoardState;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.whyxcommons.collections.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 * Class used to test the {@link BoardState} class.
 */
@ExtendWith(MockitoExtension.class)
public class BoardStateTest {

    @Nested
    class Preconditions {

        private BoardState.BoardStateBuilder builder;

        @BeforeEach
        void setup() {
            builder = BoardState.builder();
            assumeThat(builder).isNotNull();
        }

        @Test
        void cellsNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withCells(null))
                    .withMessage("cells must not be null");
        }

        @Test
        void cellsMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .build())
                    .withMessage("cells must not be null");
        }

    }

    @Nested
    class Build {

        private IBoardState boardState;

        @Mock
        private ImmutableList<ImmutableList<ICell>> cells;

        @BeforeEach
        void setup() {
            boardState = BoardState.builder()
                    .withCells(cells)
                    .build();
        }

        @Test
        void builderNotNullTest() {
            assertThat(BoardState.builder()).isNotNull();
        }

        @Test
        void boardStateNotNullTest() {
            assertThat(boardState).isNotNull();
        }

        @Test
        void cellsNotNullTest() {
            assertThat(boardState.getCells()).isNotNull();
        }

        @Test
        void getCellsTest() {
            assertThat(boardState.getCells()).isSameAs(cells);
        }

    }

}
