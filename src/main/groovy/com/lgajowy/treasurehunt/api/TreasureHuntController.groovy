package com.lgajowy.treasurehunt.api

import com.lgajowy.treasurehunt.services.TreasureService
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
    HttpResponse getPathToTreasure(Integer position) {
        treasureService.getPathToTreasure(position)
    }
}
