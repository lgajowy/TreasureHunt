package com.lgajowy.treasurehunt.services

import com.lgajowy.treasurehunt.domain.Path
import com.lgajowy.treasurehunt.domain.Position
import com.lgajowy.treasurehunt.domain.TreasureHunt
import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
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

    HttpResponse getPathToTreasure(Integer startingPosition) {
        Position position = new Position(startingPosition)
        Optional<Path> pathToTreasure = treasureHunt.findTreasure(position.row, position.column)

        if (pathToTreasure.isPresent()) {
            List<String> steps = pathToTreasure.get().toStringList()
            logSteps(steps)
            return HttpResponse.ok(['pathToTreasure': steps])

        } else {
            LOG.info("NO TREASURE")
            return HttpResponse.notFound()
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
}
