package com.whyx.openchess.implementation.model.piece;

import com.whyx.openchess.interfaces.model.board.ILocation;
import com.whyx.openchess.interfaces.model.piece.IPiece;

/**
 * @param <T> The type of location in the board game.
 * @author Sam Wykes.
 * Class represents a piece in a board game.
 */
public abstract class Piece<T extends ILocation> implements IPiece<T> {

}
