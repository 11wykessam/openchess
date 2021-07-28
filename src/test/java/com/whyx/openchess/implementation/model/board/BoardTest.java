package com.whyx.openchess.implementation.model.board;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.IBoardState;
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
 * Class used to test the {@link Board} class.
 */
@ExtendWith(MockitoExtension.class)
public class BoardTest {

    @Nested
    class Preconditions {

        private Board.BoardBuilder builder;

        @BeforeEach
        void setup() {
            builder = Board.builder();
            assumeThat(builder).isNotNull();
        }

        @Test
        void boardStateMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withBoardState(null))
                    .withMessage("boardState must not be null");
        }

        @Test
        void boardStateMustBePresentTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .build())
                    .withMessage("boardState must not be null");
        }

    }

    @Nested
    class Build {

        private IBoard board;

        @Mock
        private IBoardState boardState;

        @BeforeEach
        void setup() {
            board = Board.builder()
                    .withBoardState(boardState)
                    .build();
            // assume that board builds correctly.
            assumeThat(board).isNotNull();
        }

        @Test
        void boardStateNotNullTest() {
            assertThat(board.getState()).isNotNull();
        }

        @Test
        void getBoardStateTest() {
            assertThat(board.getState()).isSameAs(boardState);
        }

    }

}
