package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.piece.IPieceState;
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
 * Class used to test the {@link PieceState} class.
 */
@ExtendWith(MockitoExtension.class)
public class PieceStateTest {

    @Nested
    class Preconditions {

        private PieceState.PieceStateBuilder builder;

        @BeforeEach
        void setup() {
            builder = PieceState.builder();
            assumeThat(builder).isNotNull();
        }

        @Test
        void cellNotNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> builder
                            .withCell(null))
                    .withMessage("cell must not be null");
        }

    }

    @Nested
    class Build {

        @Test
        void builderNotNullTest() {
            assertThat(PieceState.builder()).isNotNull();
        }

        @Test
        void pieceStateNotNullTest() {
            assertThat(PieceState.builder()
                    .build())
                    .isNotNull();
        }

        @Test
        void getCellIsPresentOptionalNotNullTest(@Mock final ICell cell) {
            IPieceState pieceState = PieceState.builder()
                    .withCell(cell)
                    .build();
            assertThat(pieceState.getCell()).isNotNull();
        }

        @Test
        void getCellIsPresentOptionalNotEmptyTest(@Mock final ICell cell) {
            IPieceState pieceState = PieceState.builder()
                    .withCell(cell)
                    .build();
            assertThat(pieceState.getCell().isEmpty()).isFalse();
        }

        @Test
        void getCellIsPresentValueNotNullTest(@Mock final ICell cell) {
            IPieceState pieceState = PieceState.builder()
                    .withCell(cell)
                    .build();
            assertThat(pieceState.getCell().get()).isNotNull();
        }

        @Test
        void getCellIsPresentTest(@Mock final ICell cell) {
            IPieceState pieceState = PieceState.builder()
                    .withCell(cell)
                    .build();
            assertThat(pieceState.getCell().get()).isSameAs(cell);
        }

        @Test
        void getPieceIsNotPresentOptionalNotNullTest() {
            IPieceState pieceState = PieceState.builder()
                    .build();
            assertThat(pieceState.getCell()).isNotNull();
        }

        @Test
        void getPieceIsNotPresentOptionalEmptyTest() {
            IPieceState pieceState = PieceState.builder()
                    .build();
            assertThat(pieceState.getCell().isEmpty()).isTrue();
        }

    }

}