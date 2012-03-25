package com.sudokus.processor.impl;

import com.sudokus.model.Atom;
import com.sudokus.processor.Processor;
import org.apache.log4j.Logger;

/**
 * @author skrauchenia
 */
abstract class AbstractProcessor implements Processor {

    protected final Logger log = Logger.getLogger(this.getClass());
    
    protected boolean isNumberPresent(int numberCandidate, Atom[] atoms) {
        for (Atom atom : atoms) {
            if(atom.solved() && atom.getValue() == numberCandidate) {
                return true;
            }
        }
        return false;
    }

    protected boolean isNumberPresent(int numberCandidate, Atom[][] atoms) {
        for (Atom[] atomArray : atoms) {
            if(isNumberPresent(numberCandidate, atomArray)) {
                return true;
            }
        }
        return false;
    }
}
