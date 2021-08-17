package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.exceptions.PieceNotFoundException;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMoveRule;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static com.whyx.openchess.implementation.model.game.Game.GameBuilder;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.BDDMockito.given;

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

            private GameBuilder<ILocation> builder;

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

        @Nested
        class MethodPreconditions {

            private Game<ILocation> game;

            @Mock
            private IBoard<ILocation> board;

            @BeforeEach
            void setup() {
                this.game = Game.builder()
                        .withBoard(board)
                        .build();
            }

            @Test
            void isMoveLegalPieceNotNullTest(
                    @Mock final ICell<ILocation> start,
                    @Mock final ICell<ILocation> destination) {
                assertThatNullPointerException()
                        .isThrownBy(() -> game.isMoveLegal(null, start, destination))
                        .withMessage("piece must not be null");
            }

            @Test
            void isMoveLegalStartNotNullTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> destination
            ) {
                assertThatNullPointerException()
                        .isThrownBy(() -> game.isMoveLegal(piece, null, destination))
                        .withMessage("start must not be null");
            }

            @Test
            void isMoveLegalDestinationNotNullTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> start
            ) {
                assertThatNullPointerException()
                        .isThrownBy(() -> game.isMoveLegal(piece, start, null))
                        .withMessage("destination must not be null");
            }

            @Test
            void isMoveLegalStartNotOnBoardTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> start,
                    @Mock final ICell<ILocation> destination
            ) {
                assertThatThrownBy(() -> game.isMoveLegal(piece, start, destination))
                        .isExactlyInstanceOf(CellNotFoundException.class)
                        .hasMessage("cell not found");
            }

            @Test
            void isMoveLegalDestinationNotOnBoardTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> start,
                    @Mock final ICell<ILocation> destination
            ) {
                given(board.containsCell(start)).willReturn(true);

                assertThatThrownBy(() -> game.isMoveLegal(piece, start, destination))
                        .isExactlyInstanceOf(CellNotFoundException.class)
                        .hasMessage("cell not found");
            }

            @Test
            void isMoveLegalPieceNotOnStartTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> start,
                    @Mock final ICell<ILocation> destination
            ) {
                given(board.containsCell(start)).willReturn(true);
                given(board.containsCell(destination)).willReturn(true);

                assertThatThrownBy(() -> game.isMoveLegal(piece, start, destination))
                        .isExactlyInstanceOf(PieceNotFoundException.class)
                        .hasMessage("piece not found");
            }

            @Test
            void isMoveLegalWrongPieceOnStartTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final IPiece<ILocation> wrongPiece,
                    @Mock final ICell<ILocation> start,
                    @Mock final ICell<ILocation> destination
            ) {
                given(board.containsCell(start)).willReturn(true);
                given(board.containsCell(destination)).willReturn(true);
                given(start.getPiece()).willReturn(Optional.of(wrongPiece));

                assertThatThrownBy(() -> game.isMoveLegal(piece, start, destination))
                        .isExactlyInstanceOf(PieceNotFoundException.class)
                        .hasMessage("piece not found");
            }
        }

    }

    @Nested
    class Build {

        @Nested
        class AfterBuild {

            @Mock
            private IBoard<ILocation> board;

            private IGame<ILocation> game;

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

    @Nested
    class Functionality {

        @Mock
        private IBoard<ILocation> board;

        private IGame<ILocation> game;

        @BeforeEach
        void setup() {
            game = Game.builder()
                    .withBoard(board)
                    .build();
        }

        @Test
        void isMoveLegalReturnsTrueWhenAllRulesSatisfied(
                @Mock final IMoveRule<ILocation> rule,
                @Mock final IRuleBook<ILocation> ruleBook,
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> piece
        ) {
            given(piece.getRuleBook()).willReturn(ruleBook);
            given(ruleBook.getRules()).willReturn(Stream.of(rule));
            given(rule.moveConformsToRule(start, destination, piece, board)).willReturn(true);
            given(start.getPiece()).willReturn(Optional.of(piece));
            given(board.containsCell(start)).willReturn(true);
            given(board.containsCell(destination)).willReturn(true);

            assertThat(game.isMoveLegal(piece, start, destination)).isTrue();
        }

        @Test
        void isMoveLegalReturnsFalseIfRuleNotSatisfied(
                @Mock final IMoveRule<ILocation> rule,
                @Mock final IRuleBook<ILocation> ruleBook,
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> piece
        ) {
            given(piece.getRuleBook()).willReturn(ruleBook);
            given(ruleBook.getRules()).willReturn(Stream.of(rule));
            given(rule.moveConformsToRule(start, destination, piece, board)).willReturn(false);
            given(start.getPiece()).willReturn(Optional.of(piece));
            given(board.containsCell(start)).willReturn(true);
            given(board.containsCell(destination)).willReturn(true);

            assertThat(game.isMoveLegal(piece, start, destination)).isFalse();
        }

        @Test
        void getPossibleMoveReturnsMoveIfThereExistsPossibleMove(
                @Mock final IMoveRule<ILocation> rule,
                @Mock final IRuleBook<ILocation> ruleBook,
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> piece
        ) {
            given(piece.getRuleBook()).willReturn(ruleBook);
            given(ruleBook.getRules()).willReturn(Stream.of(rule));
            given(rule.moveConformsToRule(start, destination, piece, board)).willReturn(true);
            given(start.getPiece()).willReturn(Optional.of(piece));
            given(board.containsCell(start)).willReturn(true);
            given(board.containsCell(destination)).willReturn(true);
            given(board.getCells()).willReturn(Stream.of(start, destination));

            assertThat(game.getPossibleMovesFromCell(start).size()).isEqualTo(1);
        }


    }

}
