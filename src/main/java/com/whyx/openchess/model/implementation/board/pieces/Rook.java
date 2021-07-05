package com.whyx.openchess.model.implementation.board.pieces;

import com.whyx.openchess.model.interfaces.board.IBoard;
import com.whyx.openchess.model.interfaces.board.pieces.IRook;

public class Rook extends Piece implements IRook {

    Rook(PieceBuilder builder) {
        super(builder);
    }

    @Override
    public boolean isLegalMove(int x, int y, IBoard currentBoard) {
        return false;
    }

}
