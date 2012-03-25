package com.sudokus.model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/14/12
 */
public class Atom {

    private int value;
    private Sector sector;
    private Atom[] row;
    private Atom[] column;
    private int sectorX;
    private int sectorY;
    private int x;
    private int y;

    public int getSectorX() {
        return sectorX;
    }

    public int getSectorY() {
        return sectorY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Atom() {
        this(0);
    }

    public Atom(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Atom[] getRow() {
        return row;
    }

    public void setRow(Atom[] row) {
        this.row = row;
    }

    public Atom[] getColumn() {
        return column;
    }

    public void setColumn(Atom[] column) {
        this.column = column;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        //return String.format("Section: %s-%s|%s-%s :Atom", sectorX, sectorY, x, y) ;
        return String.valueOf(value);
    }

    public boolean solved() {
        return value > 0;
    }

    public void setCoordinates(int sectorX, int sectorY, int x, int y) {
        this.sectorX = sectorX;
        this.sectorY = sectorY;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atom atom = (Atom) o;

        if (sectorX != atom.sectorX) return false;
        if (sectorY != atom.sectorY) return false;
        if (x != atom.x) return false;
        if (y != atom.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sectorX;
        result = 31 * result + sectorY;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public String coordinatePrettyPrint() {
        return String.format("Coordinates: Section - %s, Atom = %s", ((this.getSectorX()) * 3 + this.getSectorY() + 1), ((this.getX()) * 3 + this.getY() + 1));
    }
}
