package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.implementation.model.board.Board;
import com.whyx.openchess.implementation.model.board.Cell;
import com.whyx.openchess.implementation.model.board.location.TwoDimensionalLocation;
import com.whyx.openchess.implementation.model.piece.King;
import com.whyx.openchess.implementation.model.rule.Move;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPieceTeam;
import com.whyx.openchess.interfaces.model.rules.IMove;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sam Wykes.
 * Integration tests for the {@link Game} class.
 */
@ExtendWith(MockitoExtension.class)
public class GameAcceptanceTest {

    @Mock
    private IPieceTeam pieceTeam;

    @ParameterizedTest
    @MethodSource("legalMoveStream")
    void kingCanMoveToAdjacentTiles(final int xOffset, final int yOffset) {

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
                .withLocation(startLocation)
                .build();
        final ICell<TwoDimensionalLocation> end = Cell.<TwoDimensionalLocation>builder()
                .withLocation(destinationLocation)
                .build();

        final IBoard<TwoDimensionalLocation> board = Board.<TwoDimensionalLocation>builder()
                .withCells(Set.of(start, end))
                .build();

        final IGame<TwoDimensionalLocation> game = Game.<TwoDimensionalLocation>builder()
                .withBoard(board)
                .build();

        final IMove<TwoDimensionalLocation> move = Move.<TwoDimensionalLocation>builder()
                .withStart(start)
                .withDestination(end)
                .build();

        assertThat(game.isMoveLegal(king, move)).isTrue();

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
