package com.lgajowy.treasurehunt.services

import com.lgajowy.treasurehunt.domain.TreasureHunt
import groovy.transform.CompileStatic
import javax.inject.Inject
import javax.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
@CompileStatic
class TreasureService {

    private static final Logger LOG = LoggerFactory.getLogger(TreasureService.class);

    private TreasureHunt treasureHunt

    @Inject
    TreasureService(TreasureHunt treasureHunt) {
        this.treasureHunt = treasureHunt
    }

    Optional<List<String>> getPathToTreasure(String position) {
        Tuple2 pos = parsePosition(position)
        Optional stepsToTreasure = treasureHunt.findTreasure(pos.first, pos.second)

        if (stepsToTreasure.isPresent()) {
            List<String> steps = convertStepsToStrings(stepsToTreasure.get())
            logSteps(steps)
            return Optional.of(steps)
        } else {
            LOG.info("NO TREASURE")
            return Optional.empty()
        }
    }

    private static void logSteps(List<String> steps) {
        StringBuilder messageBuilder = new StringBuilder()
        steps.each {
            messageBuilder.append(it)
            messageBuilder.append('\n')
        }
        LOG.info(messageBuilder.toString());
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
