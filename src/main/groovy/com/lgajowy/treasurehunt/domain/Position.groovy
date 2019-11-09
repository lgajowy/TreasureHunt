package com.lgajowy.treasurehunt.domain

class Position {

    private int row

    private int column

    Position(int row, int column) {
        this.row = row
        this.column = column
    }

    Position(int position) {
        this.row = position / 10 as Integer
        this.column = position % 10
    }

    Integer toInteger() {
        return (row * 10) + column
    }

    String toString() {
        return "${row} ${column}"
    }

    int getRow() {
        return row
    }

    int getColumn() {
        return column
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Position position = (Position) o

        if (column != position.column) return false
        if (row != position.row) return false

        return true
    }
}
