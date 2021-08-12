package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.implementation.model.board.Board;
import com.whyx.openchess.implementation.model.board.Cell;
import com.whyx.openchess.implementation.model.piece.King;
import com.whyx.openchess.implementation.model.rules.Move;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.rules.IMove;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sam Wykes.
 * Integration tests for the {@link Game} class.
 */
public class GameAcceptanceTest {

    @Test
    void kingCanMoveForwardTest() {

        King king = new King();

        ICell start = Cell.builder().build();
        ICell end = Cell.builder().build();

        IBoard board = Board.builder()
                .withCells(Set.of(start, end))
                .build();

        IGame game = Game.builder()
                .withBoard(board)
                .build();

        IMove move = Move.builder()
                .withStart(start)
                .withDestination(end)
                .build();

        assertThat(game.isMoveLegal(king, move)).isTrue();

    }

}
