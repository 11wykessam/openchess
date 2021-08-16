package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.implementation.model.piece.King.KingBuilder;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * Class used to test the {@link King} class.
 */
@ExtendWith(MockitoExtension.class)
public class KingTest {

    @Nested
    class Preconditions {

        @Nested
        class BuilderPreconditions {

            private KingBuilder builder;

            @BeforeEach
            void setup() {
                builder = King.builder();
            }

            @Test
            void pieceTeamNotNullTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.withPieceTeam(null))
                        .withMessage("pieceTeam must not be null");
            }

            @Test
            void pieceTeamMustBePresentTest() {
                assertThatNullPointerException()
                        .isThrownBy(() -> builder.build())
                        .withMessage("pieceTeam must not be null");
            }

        }

        @Nested
        class Build {

            @Mock
            private IPieceTeam pieceTeam;

            private King king;

            @BeforeEach
            void setup() {
                king = King.builder()
                        .withPieceTeam(pieceTeam)
                        .build();
            }

            @Test
            void kingNotNullTest() {
                assertThat(king).isNotNull();
            }

            @Test
            void pieceTeamNotNullTest() {
                assertThat(king.getTeam()).isNotNull();
            }

            @Test
            void getPieceTeamTest() {
                assertThat(king.getTeam()).isEqualTo(pieceTeam);
            }

        }

    }

}