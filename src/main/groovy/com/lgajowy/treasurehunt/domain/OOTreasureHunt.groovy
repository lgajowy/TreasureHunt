package com.lgajowy.treasurehunt.domain

import groovy.transform.CompileStatic

/**
 * Solution #2: Object oriented approach
 * Assumptions:
 *  - There are max 9 rows and 9 columns
 *  - Game field's values are hints: first digit points next row and second digit points to next column.
 *  - we rely only on hints provided in the game field. If hints do not lead to the treasure,
 *  it will not be found.
 */
@CompileStatic
class OOTreasureHunt implements TreasureHunt {

    private final int[][] GAME_FIELD

    private final int SEGMENT_COUNT

    OOTreasureHunt(int[][] GAME_FIELD) {
        this.GAME_FIELD = GAME_FIELD
        this.SEGMENT_COUNT = GAME_FIELD.length * GAME_FIELD[0].length

    }

    @Override
    Optional<List<Integer>> findTreasure(int startRow, int startColumn) {
        Path pathToTreasure = new Path()
        Position currentPosition = new Position(startRow, startColumn)

        for (int i = 0; i < SEGMENT_COUNT; i++) {
            Position hint = new Position(GAME_FIELD[currentPosition.row - 1][currentPosition.column - 1])
            pathToTreasure.addNewStep(currentPosition)

            if (hint == currentPosition) {
                return Optional.of(pathToTreasure.toIntegers())
            } else {
                currentPosition = new Position(hint.row, hint.column)
            }
        }

        return Optional.empty()
    }

    private class Position {

        private int row

        private int column

        Position(int row, int column) {
            this.row = row
            this.column = column
        }

        Position(int position) {
            this.row = position / 10 as Integer
            this.column = position % 10
        }

        Integer toInteger() {
            return (row * 10) + column
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            Position position = (Position) o

            if (column != position.column) return false
            if (row != position.row) return false

            return true
        }
    }

    private class Path {

        private List<Position> steps

        Path() {
            this.steps = new ArrayList<>()
        }

        void addNewStep(Position step) {
            this.steps.add(step)
        }

        List<Integer> toIntegers() {
            steps.collect { it.toInteger() }
        }
    }
}
