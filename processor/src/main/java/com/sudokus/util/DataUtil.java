package com.sudokus.util;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author skrauchenia
 */
public class DataUtil {

    public static List<Atom> atomsAsList(Sector[][] data) {
        List<Atom> result = new ArrayList<>(0);
        for (Sector[] sectors : data) {
            for (Sector sector : sectors) {
                for (Atom[] atoms : sector.getAtoms()) {
                    Collections.addAll(result, atoms);
                }
            }
        }

        return result;
    }

    public static Atom[] extractRow(Sector[][] data, int sectorX, int atomX) {
        int length = data.length;
        Atom[] row = new Atom[length * length];

        int candidateNum = 0;
        for (int k = 0; k < length; k++) {
            for (int l = 0; l < length; l++) {
                row[candidateNum] = data[sectorX][k].getAtoms()[atomX][l];
                candidateNum++;
            }
        }

        return row;
    }

    public static Atom[] extractColumn(Sector[][] data, int sectorY, int atomY) {
        int length = data.length;
        Atom[] column = new Atom[length * length];

        int candidateNum = 0;
        for (int k = 0; k < length; k++) {
            for (int l = 0; l < length; l++) {
                column[candidateNum] = data[k][sectorY].getAtoms()[l][atomY];
                candidateNum++;
            }
        }
        return column;
    }
}
