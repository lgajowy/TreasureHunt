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
    Optional<Path> findTreasure(int startRow, int startColumn) {
        Path pathToTreasure = new Path()
        Position currentPosition = new Position(startRow, startColumn)

        for (int i = 0; i < SEGMENT_COUNT; i++) {
            Position hint = new Position(GAME_FIELD[currentPosition.row - 1][currentPosition.column - 1])
            pathToTreasure.addNewStep(currentPosition)

            if (hint == currentPosition) {
                return Optional.of(pathToTreasure)
            } else {
                currentPosition = new Position(hint.row, hint.column)
            }
        }
        return Optional.empty()
    }
}
