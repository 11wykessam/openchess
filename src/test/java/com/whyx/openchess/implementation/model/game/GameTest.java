package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.game.IGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.whyx.openchess.implementation.model.game.Game.GameBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * @author Sam Wykes.
 * Class used to test the {@link Game} class.
 */
@ExtendWith(MockitoExtension.class)
public class GameTest {

    /**
     * @author Sam Wykes.
     * Class used to test the preconditions of the {@link Game} class.
     */
    @Nested
    class Preconditions {

        @Nested
        class BuilderPreconditions {

            private GameBuilder builder;

            @BeforeEach
            void setup() {
                builder = Game.builder();
                assumeThat(builder).isNotNull();
            }

            @Test
            void boardNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.withBoard(null))
                        .withMessage("board must not be null");
            }

            @Test
            void boardMustBePresentTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.build())
                        .withMessage("board must not be null");
            }

        }

    }

    @Nested
    class Build {

        @Nested
        class AfterBuild {

            @Mock
            private IBoard board;

            private IGame game;

            @BeforeEach
            void setup() {
                game = Game.builder()
                        .withBoard(board)
                        .build();
            }

            @Test
            void gameNotNullTest() {
                assertThat(game).isNotNull();
            }

            @Test
            void boardNotNullTest() {
                assertThat(game.getBoard()).isNotNull();
            }

            @Test
            void getBoardTest() {
                assertThat(game.getBoard()).isSameAs(board);
            }
        }

    }

}
