package com.lgajowy.treasurehunt.domain

import spock.lang.Specification
import spock.lang.Unroll

class FunctionalTreasureHuntTest extends Specification {

    private final int[][] TEST_GAME_FIELD = [[55, 14, 25, 52, 21],
                                             [44, 31, 11, 53, 43],
                                             [24, 13, 45, 12, 34],
                                             [42, 22, 43, 32, 41],
                                             [51, 23, 33, 54, 15]]

    @Unroll
    def "should show proper path to treasure when starting from row #row and column #column"(int row, int column, List<Integer> steps) {
        given:
        FunctionalTreasureHunt treasureHunt = new FunctionalTreasureHunt(TEST_GAME_FIELD)

        expect:
        treasureHunt.findTreasure(row, column) == Optional.of(steps)

        where:
        row | column | steps
        1   | 1      | [11, 55, 15, 21, 44, 32, 13, 25, 43]
        1   | 2      | [12, 14, 52, 23, 11, 55, 15, 21, 44, 32, 13, 25, 43]
        5   | 1      | [51]
        5   | 5      | [55, 15, 21, 44, 32, 13, 25, 43]
        1   | 5      | [15, 21, 44, 32, 13, 25, 43]
    }

    def "should show proper path for smaller array as well"() {
        given:
        int[][] gameFieldWithoutTheTreasure = [[11, 11],
                                               [12, 12]]
        FunctionalTreasureHunt treasureHunt = new FunctionalTreasureHunt(gameFieldWithoutTheTreasure)

        when:
        Optional<List<Integer>> stepsToFindTheTreasure = treasureHunt.findTreasure(2, 2)

        then:
        Optional.of([22, 12, 11]) == stepsToFindTheTreasure
    }

    def "should not find the treasure when it's not there"() {
        given:
        int[][] gameFieldWithoutTheTreasure = [[55, 14, 25, 52, 21],
                                               [44, 31, 11, 53, 43],
                                               [24, 13, 45, 12, 34],
                                               [42, 22, 44, 32, 41],
                                               [55, 23, 33, 54, 15]]
        FunctionalTreasureHunt treasureHunt = new FunctionalTreasureHunt(gameFieldWithoutTheTreasure)

        when:
        Optional<List<Integer>> stepsToFindTheTreasure = treasureHunt.findTreasure(1, 1)

        then:
        Optional.empty() == stepsToFindTheTreasure
    }
}
