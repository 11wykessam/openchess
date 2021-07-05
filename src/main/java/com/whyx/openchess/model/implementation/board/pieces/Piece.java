package com.whyx.openchess.model.implementation.board.pieces;

import com.whyx.openchess.model.interfaces.board.pieces.IPiece;

import static java.util.Objects.requireNonNull;

/**
 * @author Sam Wykes.
 * Abstract class representing all pieces in traditional chess.
 */
public abstract class Piece implements IPiece {

    // ATTRIBUTES.

    private int x;

    private int y;

    private Team team;

    private boolean moved = false;

    // GETTERS.

    /**
     * Getter for x coordinate of {@link Piece} object.
     * @return int.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Getter for y coordinate of {@link Piece} object.
     * @return int.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Getter for team attribute of {@link Piece} object.
     * @return
     */
    @Override
    public Team getTeam() {
        return this.team;
    }

    /**
     * Getter for moved attribute of {@link Piece} object.
     * @return boolean.
     */
    @Override
    public boolean hasMoved() {
        return this.moved;
    }

    Piece(PieceBuilder<? extends Piece> builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.team = builder.team;
    }

    /**
     * @author Sam Wykes.
     * Abstract
     */
    public abstract static class PieceBuilder<T extends Piece> {

        // ATTRIBUTES.

        protected Integer x;

        protected Integer y;

        protected Team team;

        /**
         * Add x attribute to builder.
         * @param x x attribute of {@link Piece} being created.
         * @return this.
         */
        public PieceBuilder<T> withX(int x) {
            this.x = x;
            return this;
        }

        /**
         * Add y attribute to builder.
         * @param y y attribute of {@link Piece} being created.
         * @return this.
         */
        public PieceBuilder<T> withY(int y) {
            this.y = y;
            return this;
        }

        /**
         * Add team attribute to builder.
         * @param team team attribute of {@link Piece} being created.
         * @return this.
         */
        public PieceBuilder<T> withTeam(Team team) {
            requireNonNull(team, "team must not be null");
            this.team = team;
            return this;
        }

        /**
         * Build a {@link Piece} object.
         * @return {@link Piece} object.
         */
        public abstract T build();

    }


}
