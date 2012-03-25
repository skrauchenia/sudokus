package com.sudokus.processor.impl;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;

import java.util.Arrays;

import static com.sudokus.util.DataUtil.extractRow;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/25/12
 */
class SquaredRowProcessor extends SquaredProcessor {

    @Override
    protected Atom[] extractLine(Sector[][] data, int a, int b) {
        Atom[] line = extractRow(data, a, b);
        log.debug("Examining " + Arrays.toString(line));
        return line;
    }
}
