package com.lgajowy.treasurehunt.domain

interface TreasureHunt {

    /**
     * Finds treasure in a given game field.
     *
     * @param startRow - row position value from 1 to gameField.length (inclusive)
     * @param startColumn - column position value from 1 to gameField[0].length (inclusive)
     * @return Optional of list of steps to find the treasure or empty() if treasure could not be found.
     */
    Optional<List<Integer>> findTreasure(int startRow, int startColumn)
}