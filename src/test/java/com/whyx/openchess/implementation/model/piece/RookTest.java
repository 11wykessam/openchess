package com.whyx.openchess.implementation.model.piece;

public class RookTest extends PieceTest {

    @Override
    Piece.PieceBuilder createBuilder() {
        return Rook.builder();
    }
}
