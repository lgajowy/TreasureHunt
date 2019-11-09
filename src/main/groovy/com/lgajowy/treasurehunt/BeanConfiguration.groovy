package com.lgajowy.treasurehunt

import com.lgajowy.treasurehunt.domain.FunctionalTreasureHunt
import com.lgajowy.treasurehunt.domain.TreasureHunt
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory


/**
 * Confiuguration of Beans used in this application.
 * Switch implementation of TreasureHunt interface if you want to use the Object oriented alternative.
 */
@Factory
class BeanConfiguration {

    @Bean
    TreasureHunt treasureHunt() {
        int[][] gameField = [[55, 14, 25, 52, 21],
                             [44, 31, 11, 53, 43],
                             [24, 13, 45, 12, 34],
                             [42, 22, 43, 32, 41],
                             [51, 23, 33, 54, 15]]

        new FunctionalTreasureHunt(gameField)
    }
}
