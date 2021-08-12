package com.whyx.openchess.implementation.model.rules;

import com.whyx.openchess.implementation.model.rules.Move.MoveBuilder;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.rules.IMove;
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
 * Class used to test the {@link Move} class.
 */
@ExtendWith(MockitoExtension.class)
public class MoveTest {

    /**
     * Test the preconditions for the class.
     */
    @Nested
    class Preconditions {

        /**
         * Test the preconditions for the builder.
         */
        @Nested
        class BuilderPreconditions {

            private MoveBuilder builder;

            @BeforeEach
            void setup() {
                builder = Move.builder();
                assumeThat(builder).isNotNull();
            }

            @Test
            void startNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder
                                .withStart(null))
                        .withMessage("start must not be null");
            }

            @Test
            void startMustBePresentTest(@Mock final ICell destination) {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder
                                .withDestination(destination)
                                .build())
                        .withMessage("start must not be null");
            }

            @Test
            void destinationNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder
                                .withDestination(null))
                        .withMessage("destination must not be null");
            }

            @Test
            void destinationMustBePresentTest(@Mock final ICell start) {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder
                                .withStart(start)
                                .build())
                        .withMessage("destination must not be null");
            }

        }

    }

    /**
     * Tests that the class is built correctly.
     */
    @Nested
    class Build {

        @Mock
        private ICell start;

        @Mock
        private ICell destination;

        @Test
        void builderNotNullTest() {
            assertThat(Move.builder()).isNotNull();
        }

        @Nested
        class BuilderNotNull {

            private IMove move;

            @BeforeEach
            void setup() {
                move = Move.builder()
                        .withStart(start)
                        .withDestination(destination)
                        .build();
                assumeThat(move).isNotNull();
            }

            @Test
            void startNotNullTest() {
                assertThat(move.getStart()).isNotNull();
            }

            @Test
            void getStartTest() {
                assertThat(move.getStart()).isSameAs(start);
            }

            @Test
            void destinationNotNullTest() {
                assertThat(move.getDestination()).isNotNull();
            }

            @Test
            void getDestinationTest() {
                assertThat(move.getDestination()).isSameAs(destination);
            }

        }

    }


}
