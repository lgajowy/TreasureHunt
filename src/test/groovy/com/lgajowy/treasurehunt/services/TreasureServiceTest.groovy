package com.lgajowy.treasurehunt.services


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
        TreasureService service = new TreasureService(treasureHuntMock)

        when:
        HttpResponse actualResult = service.getPathToTreasure(11)

        then:
        treasureHuntMock.findTreasure(_, _) >> [11, 12, 13, 14]

        and:
        actualResult.status() == HttpStatus.OK
        actualResult.body() == [pathToTreasure: ["1 1", "1 2", "1 3", "1 4"]]
    }
}
