package com.lgajowy.treasurehunt.services

import com.lgajowy.treasurehunt.domain.Path
import com.lgajowy.treasurehunt.domain.Position
import com.lgajowy.treasurehunt.domain.TreasureHunt
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.Specification

class TreasureServiceTest extends Specification {

    TreasureService treasureService

    TreasureHunt treasureHuntMock = Mock()

    void setup() {
        treasureService = new TreasureService(treasureHuntMock)
    }

    def "should return path to treasure as list of strings"() {
        given:
        Path expectedPath = new Path()
        expectedPath.addNewStep(new Position(11))
        expectedPath.addNewStep(new Position(12))
        expectedPath.addNewStep(new Position(13))
        expectedPath.addNewStep(new Position(14))

        and:
        TreasureService service = new TreasureService(treasureHuntMock)

        when:
        HttpResponse actualResult = service.getPathToTreasure(11)

        then:
        treasureHuntMock.findTreasure(_, _) >> Optional.of(expectedPath)

        and:
        actualResult.status() == HttpStatus.OK
        actualResult.body() == [pathToTreasure: ["1 1", "1 2", "1 3", "1 4"]]
    }
}
