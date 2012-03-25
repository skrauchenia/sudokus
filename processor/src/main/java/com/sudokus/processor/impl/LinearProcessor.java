package com.sudokus.processor.impl;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;
import com.sudokus.processor.ResolvedAtomsStorage;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @author skrauchenia
 */
abstract class LinearProcessor extends AbstractProcessor {

    protected Logger log = Logger.getLogger(this.getClass());

    public void process(Sector[][] data) {
        int length = data.length;

        for (int i = 0; i < (length); i++) {
            for (int j = 0; j < length; j++) {
                Atom[] column = extractLine(data, i, j);
                Map<Integer, List<Atom>> candidates = createPreFilledCandidateMap(length * length);
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

                for (Map.Entry<Integer, List<Atom>> entry : candidates.entrySet()) {
                    List<Atom> value = entry.getValue();
                    if(value.size() == 1) {
                        Atom winner = value.get(0);
                        log.info("Winner found: " + winner.coordinatePrettyPrint());
                        winner.setValue(entry.getKey());
                        ResolvedAtomsStorage.storage().add(winner);
                    }
                }
            }
        }
    }

    protected abstract Atom[] extractLine(Sector[][] data, int x, int y);
}
