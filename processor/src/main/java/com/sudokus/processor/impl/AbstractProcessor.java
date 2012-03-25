package com.sudokus.processor.impl;

import com.sudokus.model.Atom;
import com.sudokus.processor.Processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author skrauchenia
 */
abstract class AbstractProcessor implements Processor {

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

    protected Map<Integer, List<Atom>> createPreFilledCandidateMap(int size) {
        Map<Integer, List<Atom>> candidates = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            candidates.put(i+1, new ArrayList<Atom>(0));
        }
        return candidates;
    }
}
