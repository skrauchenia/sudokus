package com.sudokus.processor.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.sudokus.model.Atom;
import com.sudokus.processor.ResolvedAtomsStorage;

import java.util.List;

/**
 * @author skrauchenia
 */
class SingletonProcessor extends AbstractProcessor {

    @Override
    protected void analyzeCandidates(List<ArrayListMultimap<Integer, Atom>> candidatesData) {
        for (ArrayListMultimap<Integer, Atom> candidate : candidatesData) {
            processCandidateLine(candidate);
        }
    }

    @Override
    protected List<ArrayListMultimap<Integer, Atom>> processDataOnCandidateLevel(List<Atom[]> dataChunks) {
        int length = dataChunks.size();
        List<ArrayListMultimap<Integer, Atom>> data = Lists.newArrayList();
        
        for (Atom[] chunk : dataChunks) {

            ArrayListMultimap<Integer, Atom> candidates = ArrayListMultimap.create();
            for (int k = 0; k < length; k++) {
                int numberCandidate = k + 1;

                for (int l = 0; l < length; l++) {
                    Atom candidate = chunk[l];
                    boolean present = candidate.solved(); // don't need to process this candidate if it already solved
                    present = present || super.isNumberPresent(numberCandidate, candidate.getRow());
                    present = present || super.isNumberPresent(numberCandidate, candidate.getColumn());
                    present = present || super.isNumberPresent(numberCandidate, candidate.getSector().getAtoms());

                    if (!present) {
                        candidates.get(numberCandidate).add(candidate);
                    }
                }
            }

            if (!candidates.isEmpty()) {
                data.add(candidates);
            }
        }
        return data;
    }

    protected void processCandidateLine(ArrayListMultimap<Integer, Atom> line) {
        for (Integer key : line.keySet()) {
            List<Atom> value = line.get(key);
            if (value.size() == 1) {
                Atom winner = value.get(0);
                log.info("Winner found: " + winner.coordinatePrettyPrint());
                winner.setValue(key);
                ResolvedAtomsStorage.storage().add(winner);
            }
        }
    }
}
