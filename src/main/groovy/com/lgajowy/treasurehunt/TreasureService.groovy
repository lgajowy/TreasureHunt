package com.lgajowy.treasurehunt

import groovy.transform.CompileStatic
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@CompileStatic
class TreasureService {

    private TreasureHunt treasureHunt

    @Inject
    TreasureService(TreasureHunt treasureHunt) {
        this.treasureHunt = treasureHunt
    }

    Optional<List<String>> getPathToTreasure(String position) {
        Tuple2 pos = parsePosition(position)
        Optional stepsToTreasure = treasureHunt.findTreasure(pos.first, pos.second)

        if (stepsToTreasure.isPresent()) {
            return Optional.of(convertStepsToStrings(stepsToTreasure.get()))
        } else {
            return Optional.empty()
        }
    }

    private static Tuple2<Integer, Integer> parsePosition(String position) {
        Integer positionNumber = position.toInteger()
        return new Tuple2<Integer, Integer>(positionNumber / 10 as Integer, positionNumber % 10)
    }

    private static List<String> convertStepsToStrings(List<Integer> steps) {
        steps.collect {
            Integer row = it / 10 as Integer
            Integer column = it % 10
            "${row} ${column}" as String
        }
    }
}
