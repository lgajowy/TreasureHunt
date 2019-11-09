package com.lgajowy.treasurehunt.domain

class Path {

    private List<Position> steps

    Path() {
        this.steps = new ArrayList<>()
    }

    void addNewStep(Position step) {
        this.steps.add(step)
    }

    List<Integer> toIntegers() {
        steps.collect { it.toInteger() }
    }

    List<String> toStringList() {
        steps.collect {
            it.toString()
        }
    }
}