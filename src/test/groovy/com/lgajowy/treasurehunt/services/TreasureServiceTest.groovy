package com.lgajowy.treasurehunt.services

import com.lgajowy.treasurehunt.domain.TreasureHunt
import com.lgajowy.treasurehunt.services.TreasureService
import spock.lang.Specification

class TreasureServiceTest extends Specification {

    TreasureHunt treasureHuntMock = Mock()

    def "should return path to treasure as list of strings"() {
        given:
        TreasureService service = new TreasureService(treasureHuntMock)

        when:
        def actualResult = service.getPathToTreasure("11")

        then:
        treasureHuntMock.findTreasure(_, _) >> [11, 12, 13, 14]

        and:
        actualResult == Optional.of(["1 1", "1 2", "1 3", "1 4"])
    }
}
