package com.lgajowy.treasurehunt

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller("/")
@CompileStatic
class TreasureHuntController {

    private TreasureService treasureService

    @Inject
    TreasureHuntController(TreasureService treasureService) {
        this.treasureService = treasureService
    }

    @Get("/treasures/{position}")
    HttpResponse getPathToTreasure(String position) {
        Optional<List<String>> pathToTreasure = treasureService.getPathToTreasure(position)

        if (pathToTreasure.isPresent()) {
            return HttpResponse.ok(['pathToTreasure': pathToTreasure.get()])
        } else {
            return HttpResponse.notFound()
        }
    }
}
