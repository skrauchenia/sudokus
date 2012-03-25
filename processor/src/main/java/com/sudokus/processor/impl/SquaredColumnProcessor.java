package com.sudokus.processor.impl;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;

import java.util.Arrays;

import static com.sudokus.util.DataUtil.extractColumn;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/25/12
 */
class SquaredColumnProcessor extends SquaredProcessor {

    @Override
    protected Atom[] extractLine(Sector[][] data, int a, int b) {
        Atom[] line = extractColumn(data, a, b);
        log.debug("Examining " + Arrays.toString(line));
        return line;
    }

}
