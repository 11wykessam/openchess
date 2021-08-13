package com.whyx.openchess.implementation.model.game;

import com.whyx.openchess.implementation.exceptions.CellNotFoundException;
import com.whyx.openchess.implementation.model.rules.Move;
import com.whyx.openchess.interfaces.model.board.IBoard;
import com.whyx.openchess.interfaces.model.board.ICell;
import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.game.IGame;
import com.whyx.openchess.interfaces.model.piece.IPiece;
import com.whyx.openchess.interfaces.model.rules.IMove;
import com.whyx.openchess.interfaces.model.rules.IRule;
import com.whyx.openchess.interfaces.model.rules.IRuleBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
            void isMoveLegalPieceNotNullTest(@Mock final Move<ILocation> move) {
                assertThatNullPointerException()
                        .isThrownBy(() -> game.isMoveLegal(null, move))
                        .withMessage("piece must not be null");
            }

            @Test
            void isMoveLegalMoveNotNullTest(@Mock final IPiece<ILocation> piece) {
                assertThatNullPointerException()
                        .isThrownBy(() -> game.isMoveLegal(piece, null))
                        .withMessage("move must not be null");
            }

            @Test
            void isMoveLegalStartNotOnBoardTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> start,
                    @Mock final IMove<ILocation> move
            ) {
                given(move.getStart()).willReturn(start);

                assertThatThrownBy(() -> game.isMoveLegal(piece, move))
                        .isExactlyInstanceOf(CellNotFoundException.class)
                        .hasMessage("cell not found");
            }

            @Test
            void isMoveLegalDestinationNotOnBoardTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> start,
                    @Mock final IMove<ILocation> move
            ) {
                given(move.getStart()).willReturn(start);

                assertThatThrownBy(() -> game.isMoveLegal(piece, move))
                        .isExactlyInstanceOf(CellNotFoundException.class)
                        .hasMessage("cell not found");
            }

            @Test
            void isMoveLegalPieceNotOnStartTest(
                    @Mock final IPiece<ILocation> piece,
                    @Mock final ICell<ILocation> start,
                    @Mock final IMove<ILocation> move
            ) {
                given(move.getStart()).willReturn(start);

                assertThatThrownBy(() -> game.isMoveLegal(piece, move))
                        .isExactlyInstanceOf(CellNotFoundException.class)
                        .hasMessage("cell not found");
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

        @Test
        void isMoveLegalReturnsTrueWhenAllRulesSatisfied(
                @Mock final IRule<ILocation> rule,
                @Mock final IRuleBook<ILocation> ruleBook,
                @Mock final IMove<ILocation> move,
                @Mock final ICell<ILocation> start,
                @Mock final ICell<ILocation> destination,
                @Mock final IPiece<ILocation> piece,
                @Mock final IBoard<ILocation> board
        ) {
            final Stream<IRule<ILocation>> ruleStream = Stream.of(rule);
            given(piece.getRuleBook()).willReturn(ruleBook);
            given(ruleBook.getRules()).willReturn(ruleStream);
            given(rule.moveConformsToRule(move, piece, board)).willReturn(true);
            given(move.getStart()).willReturn(start);
            given(move.getDestination()).willReturn(destination);
            given(board.containsCell(start)).willReturn(true);
            given(board.containsCell(destination)).willReturn(true);

            final IGame<ILocation> game = Game.builder()
                    .withBoard(board)
                    .build();

            assertThat(game.isMoveLegal(piece, move)).isTrue();
        }

    }

}
