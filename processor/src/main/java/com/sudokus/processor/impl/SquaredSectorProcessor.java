package com.sudokus.processor.impl;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;

import java.util.Arrays;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/25/12
 */
class SquaredSectorProcessor extends SquaredProcessor {

    @Override
    protected Atom[] extractLine(Sector[][] data, int a, int b) {
        Atom[][] atoms = data[a][b].getAtoms();
        int length = atoms.length;
        Atom[] line = new Atom[length * length];
        int currentPosition = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                line[currentPosition] = atoms[i][j];
                currentPosition++;
            }
        }
        log.debug("Examining " + Arrays.toString(line));
        return line;
    }
}
