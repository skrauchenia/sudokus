package com.sudokus.processor;

import com.sudokus.model.Atom;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/25/12
 */
public interface AtomStorageListener {

    void onAction(Atom atom);
}
