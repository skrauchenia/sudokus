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
abstract class SquaredProcessor extends LinearProcessor {

    @Override
    public void process(Sector[][] data) {
        int length = data.length;
        List<ArrayListMultimap<Atom, Integer>> candidatesResult = Lists.newArrayList();
        for (int i = 0; i < (length); i++) {
            for (int j = 0; j < length; j++) {
                Atom[] column = extractLine(data, i, j);
                ArrayListMultimap<Atom, Integer> candidates = ArrayListMultimap.create();
                for (int k = 0; k < length * length; k++) {
                    int numberCandidate = k + 1;

                    for (int l = 0; l < length * length; l++) {
                        Atom candidate = column[l];
                        boolean present = candidate.solved(); // don't need to process this candidate if it already solved
                        present = present || super.isNumberPresent(numberCandidate, candidate.getRow());
                        present = present || super.isNumberPresent(numberCandidate, candidate.getColumn());
                        present = present || super.isNumberPresent(numberCandidate, candidate.getSector().getAtoms());

                        if (!present) {
                            candidates.put(candidate, numberCandidate);
                        }
                    }
                }
                candidatesResult.add(candidates);
            }
        }

        processCandidates(candidatesResult, data);
    }

    protected void processCandidates(List<ArrayListMultimap<Atom,Integer>> candidatesResult, Sector[][] data) {
        for (ArrayListMultimap<Atom, Integer> line : candidatesResult) {
            Multiset<List<Integer>> atomCandidates = HashMultiset.create();
            for (Atom key : line.keySet()) {
                atomCandidates.add(line.get(key));
            }

            for (List<Integer> candidates : atomCandidates) {
                if(candidates.size() == atomCandidates.count(candidates)) {
                    for (Atom atom : line.keySet()) {
                        List<Integer> integers = line.get(atom);
                        if(integers.equals(candidates)) {
                            continue;
                        }

                        Iterator<Integer> iterator = integers.iterator();
                        while (iterator.hasNext()) {
                            if(candidates.contains(iterator.next())) {
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
