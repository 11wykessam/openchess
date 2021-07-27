package com.whyx.openchess.model.board;

import com.whyx.openchessinterface.model.piece.IPiece;
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
 * Class used to test the {@link CellState} class.
 */
@ExtendWith(MockitoExtension.class)
public class CellStateTest {

    @Nested
    class Preconditions {

        private CellState.CellStateBuilder cellStateBuilder;

        @BeforeEach
        void setup() {
            cellStateBuilder = CellState.builder();
            assumeThat(cellStateBuilder).isNotNull();
        }

        @Test
        void pieceMustNotBeNullTest() {
            assertThatNullPointerException()
                    .isThrownBy(() -> cellStateBuilder
                            .withPiece(null))
                    .withMessage("piece must not be null");
        }

    }

    @Nested
    class Build {

        @Test
        void builderNotNullTest() {
            assertThat(CellState.builder()).isNotNull();
        }

        @Test
        void cellStateNotNullTest(@Mock final IPiece piece) {
            CellState cellState = CellState.builder()
                    .withPiece(piece)
                    .build();
            assertThat(cellState).isNotNull();
        }

        @Test
        void getPieceIsPresentOptionalNotNullTest(@Mock final IPiece piece) {
            CellState cellState = CellState.builder()
                    .withPiece(piece)
                    .build();
            assertThat(cellState.getPiece()).isNotNull();
        }

        @Test
        void getPieceIsPresentOptionalValueNotNullTest(@Mock final IPiece piece) {
            CellState cellState = CellState.builder()
                    .withPiece(piece)
                    .build();
            assertThat(cellState.getPiece().get()).isNotNull();
        }

        @Test
        void getPieceIsPresentTest(@Mock final IPiece piece) {
            CellState cellState = CellState.builder()
                    .withPiece(piece)
                    .build();
            assertThat(cellState.getPiece().get()).isSameAs(piece);
        }

        @Test
        void getPieceIsNotPresentOptionalNotNullTest() {
            CellState cellState = CellState.builder()
                    .build();
            assertThat(cellState.getPiece()).isNotNull();
        }

        @Test
        void getPieceIsNotPresentOptionalEmptyTest() {
            CellState cellState = CellState.builder()
                    .build();
            assertThat(cellState.getPiece().isEmpty()).isTrue();
        }


    }

}
