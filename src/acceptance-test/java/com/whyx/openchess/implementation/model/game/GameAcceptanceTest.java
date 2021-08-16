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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sam Wykes.
 * Integration tests for the {@link Game} class.
 */
@ExtendWith(MockitoExtension.class)
public class GameAcceptanceTest {

    @Test
    void kingCanMoveForwardTest(@Mock final IPieceTeam pieceTeam) {

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
                .withXSupplier(() -> startX)
                .withYSupplier(() -> startY + 1)
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

}
