package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
import com.whyx.openchess.implementation.model.board.Board;
import com.whyx.openchess.implementation.model.board.Cell;
import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.implementation.model.game.Game;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

/**
 * @author Sam Wykes.
 * Integration tests for the {@link Game} class.
 */
@ExtendWith(MockitoExtension.class)
public class KingAcceptanceTest {

    @Mock
    private IPieceTeam pieceTeam;

    @ParameterizedTest
    @MethodSource("legalMoveStream")
    void canMoveToAdjacentTiles(final int xOffset, final int yOffset) {

        final int startX = 0;
        final int startY = 0;

        final King king = King.builder()
                .withPieceTeam(pieceTeam)
                .build();

        final TwoDimensionalLocation startLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX)
                .withYSupplier(() -> startY)
                .build();

        final TwoDimensionalLocation destinationLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX + xOffset)
                .withYSupplier(() -> startY + yOffset)
                .build();

        final ICell<TwoDimensionalLocation> start = Cell.<TwoDimensionalLocation>builder()
                .withPiece(king)
                .withLocation(startLocation)
                .build();
        final ICell<TwoDimensionalLocation> destination = Cell.<TwoDimensionalLocation>builder()
                .withLocation(destinationLocation)
                .build();

        final IBoard<TwoDimensionalLocation> board = Board.<TwoDimensionalLocation>builder()
                .withCells(Set.of(start, destination))
                .build();

        final IGame<TwoDimensionalLocation> game = Game.<TwoDimensionalLocation>builder()
                .withBoard(board)
                .build();

        assertThat(game.isMoveLegal(king, start, destination)).isTrue();
    }

    @Test
    void pieceTooFarTest() {

        final int startX = 0;
        final int startY = 0;

        final King king = King.builder()
                .withPieceTeam(pieceTeam)
                .build();

        final TwoDimensionalLocation startLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX)
                .withYSupplier(() -> startY)
                .build();

        final TwoDimensionalLocation destinationLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX + 2)
                .withYSupplier(() -> startY)
                .build();

        final ICell<TwoDimensionalLocation> start = Cell.<TwoDimensionalLocation>builder()
                .withPiece(king)
                .withLocation(startLocation)
                .build();
        final ICell<TwoDimensionalLocation> destination = Cell.<TwoDimensionalLocation>builder()
                .withLocation(destinationLocation)
                .build();

        final IBoard<TwoDimensionalLocation> board = Board.<TwoDimensionalLocation>builder()
                .withCells(Set.of(start, destination))
                .build();

        final IGame<TwoDimensionalLocation> game = Game.<TwoDimensionalLocation>builder()
                .withBoard(board)
                .build();

        assertThat(game.isMoveLegal(king, start, destination)).isFalse();

    }

    @Test
    void destinationOccupiedTest(@Mock final IPiece<TwoDimensionalLocation> opponent) {
        final int startX = 0;
        final int startY = 0;

        given(opponent.getTeam()).willReturn(pieceTeam);

        final King king = King.builder()
                .withPieceTeam(pieceTeam)
                .build();

        final TwoDimensionalLocation startLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX)
                .withYSupplier(() -> startY)
                .build();

        final TwoDimensionalLocation destinationLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX + 1)
                .withYSupplier(() -> startY)
                .build();

        final ICell<TwoDimensionalLocation> start = Cell.<TwoDimensionalLocation>builder()
                .withPiece(king)
                .withLocation(startLocation)
                .build();
        final ICell<TwoDimensionalLocation> destination = Cell.<TwoDimensionalLocation>builder()
                .withPiece(opponent)
                .withLocation(destinationLocation)
                .build();

        final IBoard<TwoDimensionalLocation> board = Board.<TwoDimensionalLocation>builder()
                .withCells(Set.of(start, destination))
                .build();

        final IGame<TwoDimensionalLocation> game = Game.<TwoDimensionalLocation>builder()
                .withBoard(board)
                .build();

        assertThat(game.isMoveLegal(king, start, destination)).isFalse();
    }

    @Test
    void cannotMoveOffBoardTest() {
        final int startX = 0;
        final int startY = 0;

        final King king = King.builder()
                .withPieceTeam(pieceTeam)
                .build();

        final TwoDimensionalLocation startLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX)
                .withYSupplier(() -> startY)
                .build();

        final TwoDimensionalLocation destinationLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX + 1)
                .withYSupplier(() -> startY)
                .build();

        final ICell<TwoDimensionalLocation> start = Cell.<TwoDimensionalLocation>builder()
                .withPiece(king)
                .withLocation(startLocation)
                .build();
        final ICell<TwoDimensionalLocation> destination = Cell.<TwoDimensionalLocation>builder()
                .withLocation(destinationLocation)
                .build();

        final IBoard<TwoDimensionalLocation> board = Board.<TwoDimensionalLocation>builder()
                .withCells(Set.of(start))
                .build();

        final IGame<TwoDimensionalLocation> game = Game.<TwoDimensionalLocation>builder()
                .withBoard(board)
                .build();

        assertThatThrownBy(() -> game.isMoveLegal(king, start, destination))
                .isExactlyInstanceOf(CellNotFoundException.class);
    }

    @Test
    void kingNotOnStartTest() {
        final int startX = 0;
        final int startY = 0;

        final King king = King.builder()
                .withPieceTeam(pieceTeam)
                .build();

        final TwoDimensionalLocation startLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX)
                .withYSupplier(() -> startY)
                .build();

        final TwoDimensionalLocation destinationLocation = TwoDimensionalLocation.builder()
                .withXSupplier(() -> startX + 1)
                .withYSupplier(() -> startY)
                .build();

        final ICell<TwoDimensionalLocation> start = Cell.<TwoDimensionalLocation>builder()
                .withLocation(startLocation)
                .build();
        final ICell<TwoDimensionalLocation> destination = Cell.<TwoDimensionalLocation>builder()
                .withLocation(destinationLocation)
                .build();

        final IBoard<TwoDimensionalLocation> board = Board.<TwoDimensionalLocation>builder()
                .withCells(Set.of(start, destination))
                .build();

        final IGame<TwoDimensionalLocation> game = Game.<TwoDimensionalLocation>builder()
                .withBoard(board)
                .build();

        assertThatThrownBy(() -> game.isMoveLegal(king, start, destination))
                .isExactlyInstanceOf(PieceNotFoundException.class);
    }

    private static Stream<Arguments> legalMoveStream() {
        return Stream.of(
                Arguments.of(-1, -1),
                Arguments.of(-1, 0),
                Arguments.of(-1, 1),
                Arguments.of(0, -1),
                Arguments.of(0, 0),
                Arguments.of(0, 1),
                Arguments.of(1, -1),
                Arguments.of(1, 0),
                Arguments.of(1, 1)
        );
    }

}
