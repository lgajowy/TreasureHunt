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
class TreasureHunt {

    private final int[][] gameField

    private final int segmentCount

    TreasureHunt(int[][] gameField) {
        this.gameField = gameField
        this.segmentCount = gameField.length * gameField[0].length
    }

    /**
     * Finds treasure in a given game field.
     *
     * @param startRow - row position value from 1 to gameField.length (inclusive)
     * @param startColumn - column position value from 1 to gameField[0].length (inclusive)
     * @return Optional of list of steps to find the treasure or empty() if treasure could not be found.
     */
    Optional<List<Integer>> findTreasure(int startRow, int startColumn) {
        return lookForTreasure(startRow - 1, startColumn - 1, [], 0)
    }

    private Optional<List<Integer>> lookForTreasure(int row, int column, List<Integer> stepsSoFar, int stepCount) {
        if (stepCount > segmentCount) {
            return Optional.empty()
        }

        Integer newStep = "${row + 1}${column + 1}".toInteger()
        stepsSoFar.add(newStep)

        if ("${row + 1}${column + 1}" == "${gameField[row][column]}") {
            return Optional.of(stepsSoFar)
        } else {
            int value = gameField[row][column]
            int newRow = (value / 10) - 1 as Integer
            int newCol = (value % 10) - 1

            return lookForTreasure(newRow, newCol, stepsSoFar, stepCount + 1)
        }
    }
}
