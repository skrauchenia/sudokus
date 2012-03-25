package com.sudokus.processor;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;

/**
 * @author skrauchenia
 */
public interface Processor {
    
    void process(Sector[][] data);
}
