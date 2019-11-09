package com.lgajowy.treasurehunt.domain

import groovy.transform.CompileStatic

/**
 * Solution #1: uses native data structures and functional concepts (optional, tail recursion).
 * Assumptions:
 *  - There are max 9 rows and 9 columns
 *  - Game field's values are hints: first digit points next row and second digit points to next column.
 *  - we rely only on hints provided in the game field. If hints do not lead to the treasure,
 *  it will not be found.
 */
@CompileStatic
class FunctionalTreasureHunt implements TreasureHunt {

    private final int[][] GAME_FIELD

    private final int SEGMENT_COUNT

    FunctionalTreasureHunt(int[][] GAME_FIELD) {
        this.GAME_FIELD = GAME_FIELD
        this.SEGMENT_COUNT = GAME_FIELD.length * GAME_FIELD[0].length
    }

    @Override
    Optional<List<Integer>> findTreasure(int startRow, int startColumn) {
        return lookForTreasure(startRow - 1, startColumn - 1, [], 0)
    }

    private Optional<List<Integer>> lookForTreasure(int row, int column, List<Integer> stepsSoFar, int stepCount) {
        if (stepCount > SEGMENT_COUNT) {
            return Optional.empty()
        }

        Integer newStep = "${row + 1}${column + 1}".toInteger()
        stepsSoFar.add(newStep)

        if ("${row + 1}${column + 1}" == "${GAME_FIELD[row][column]}") {
            return Optional.of(stepsSoFar)
        } else {
            int value = GAME_FIELD[row][column]
            int newRow = (value / 10) - 1 as Integer
            int newCol = (value % 10) - 1

            return lookForTreasure(newRow, newCol, stepsSoFar, stepCount + 1)
        }
    }
}
