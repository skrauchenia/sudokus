package com.sudokus.processor.impl;

import com.google.common.collect.*;
import com.sudokus.model.Atom;
import com.sudokus.model.Sector;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/25/12
 */
class OpenPairsProcessor extends SingletonProcessor {

    @Override
    protected List<ArrayListMultimap<Atom, Integer>> processDataOnAtomLevel(List<Atom[]> dataChunks) {
        int length = dataChunks.size();
        List<ArrayListMultimap<Atom, Integer>> candidatesResult = Lists.newArrayList();
        for (Atom[] chunk : dataChunks) {

            ArrayListMultimap<Atom, Integer> candidates = ArrayListMultimap.create();
            for (int k = 0; k < length; k++) {
                int numberCandidate = k + 1;

                for (int l = 0; l < length; l++) {
                    Atom candidate = chunk[l];
                    boolean present = candidate.solved(); // don't need to process this candidate if it already solved
                    present = present || super.isNumberPresent(numberCandidate, candidate.getRow());
                    present = present || super.isNumberPresent(numberCandidate, candidate.getColumn());
                    present = present || super.isNumberPresent(numberCandidate, candidate.getSector().getAtoms());

                    if (!present) {
                        candidates.put(candidate, numberCandidate);
                    }
                }
            }
            if (!candidates.isEmpty()) {
                candidatesResult.add(candidates);
            }
        }
        return candidatesResult;
    }

    protected void analyzeAtoms(List<ArrayListMultimap<Atom, Integer>> candidatesResult) {
        for (ArrayListMultimap<Atom, Integer> line : candidatesResult) {
            Multiset<List<Integer>> atomCandidates = HashMultiset.create();
            for (Atom key : line.keySet()) {
                atomCandidates.add(line.get(key));
            }

            for (List<Integer> candidates : atomCandidates) {
                if (candidates.size() == atomCandidates.count(candidates)) {
                    for (Atom atom : line.keySet()) {
                        List<Integer> integers = line.get(atom);
                        if (integers.equals(candidates)) {
                            continue;
                        }

                        Iterator<Integer> iterator = integers.iterator();
                        while (iterator.hasNext()) {
                            if (candidates.contains(iterator.next())) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }

            ArrayListMultimap<Integer, Atom> updatedDate = ArrayListMultimap.create();
            for (Map.Entry<Atom, Integer> entry : line.entries()) {
                updatedDate.put(entry.getValue(), entry.getKey());
            }

            super.processCandidateLine(updatedDate);
        }
    }
}
