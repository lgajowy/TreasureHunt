package com.lgajowy.treasurehunt

import com.lgajowy.treasurehunt.domain.FunctionalTreasureHunt
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory

@Factory
class BeanConfiguration {
    @Bean
    FunctionalTreasureHunt treasureHunt() {
        int[][] gameField = [[55, 14, 25, 52, 21],
                             [44, 31, 11, 53, 43],
                             [24, 13, 45, 12, 34],
                             [42, 22, 43, 32, 41],
                             [51, 23, 33, 54, 15]]

        new FunctionalTreasureHunt(gameField)
    }
}
