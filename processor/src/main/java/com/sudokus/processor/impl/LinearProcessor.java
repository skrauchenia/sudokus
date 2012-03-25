package com.sudokus.processor.impl;

import com.google.common.collect.ArrayListMultimap;
import com.sudokus.model.Atom;
import com.sudokus.model.Sector;
import com.sudokus.processor.ResolvedAtomsStorage;

import java.util.List;

/**
 * @author skrauchenia
 */
abstract class LinearProcessor extends AbstractProcessor {

    public void process(Sector[][] data) {
        int length = data.length;

        for (int i = 0; i < (length); i++) {
            for (int j = 0; j < length; j++) {
                Atom[] column = extractLine(data, i, j);
                ArrayListMultimap<Integer, Atom> candidates = ArrayListMultimap.create();
                for (int k = 0; k < length * length; k++) {
                    int numberCandidate = k + 1;

                    for (int l = 0; l < length * length; l++) {
                        Atom candidate = column[l];
                        boolean present = candidate.solved(); // don't need to process this candidate if it already solved
                        present = present || super.isNumberPresent(numberCandidate, candidate.getRow());
                        present = present || super.isNumberPresent(numberCandidate, candidate.getColumn());
                        present = present || super.isNumberPresent(numberCandidate, candidate.getSector().getAtoms());

                        if(!present) {
                            candidates.get(numberCandidate).add(candidate);
                        }
                    }
                }

                processCandidateLine(candidates);
            }
        }
    }

    protected void processCandidateLine(ArrayListMultimap<Integer, Atom> candidates) {
        for (Integer key : candidates.keySet()) {
            List<Atom> value = candidates.get(key);
            if(value.size() == 1) {
                Atom winner = value.get(0);
                log.info("Winner found: " + winner.coordinatePrettyPrint());
                winner.setValue(key);
                ResolvedAtomsStorage.storage().add(winner);
            }
        }
    }

    protected abstract Atom[] extractLine(Sector[][] data, int x, int y);
}
