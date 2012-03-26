package com.sudokus.processor.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.sudokus.model.Atom;
import com.sudokus.model.Sector;
import com.sudokus.processor.Processor;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static com.sudokus.util.DataUtil.extractColumn;
import static com.sudokus.util.DataUtil.extractRow;

/**
 * @author skrauchenia
 */
abstract class AbstractProcessor implements Processor {

    protected final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void process(Sector[][] data) {
        processRows(data);
        processColumns(data);
        processSectors(data);
    }

    protected void processSectors(Sector[][] data) {
        List<Atom[]> dataChunks = assembleSectors(data);
        List<ArrayListMultimap<Atom, Integer>> atomsData = processSectorsOnAtomLevel(dataChunks);
        List<ArrayListMultimap<Integer, Atom>> candidatesData = processSectorsOnCandidateLevel(dataChunks);
        analyzeSectorsAtoms(atomsData);
        analyzeSectorsCandidates(candidatesData);
    }

    protected void processColumns(Sector[][] data) {
        List<Atom[]> dataChunks = assembleColumns(data);
        List<ArrayListMultimap<Atom, Integer>> atomsData = processColumnsOnAtomLevel(dataChunks);
        List<ArrayListMultimap<Integer, Atom>> candidatesData = processColumnsOnCandidateLevel(dataChunks);
        analyzeColumnsAtoms(atomsData);
        analyzeColumnsCandidates(candidatesData);
    }


    protected void processRows(Sector[][] data) {
        List<Atom[]> dataChunks = assembleRows(data);
        List<ArrayListMultimap<Atom, Integer>> atomsData = processRowsOnAtomLevel(dataChunks);
        List<ArrayListMultimap<Integer, Atom>> candidatesData = processRowsOnCandidateLevel(dataChunks);
        analyzeRowsAtoms(atomsData);
        analyzeRowsCandidates(candidatesData);
    }

    /** analyzers */

    /**
     * Analyze rows aggregated data about candidates
     *
     * @param candidatesData data to analyze
     */
    protected void analyzeRowsCandidates(List<ArrayListMultimap<Integer, Atom>> candidatesData) {
        analyzeCandidates(candidatesData);
    }

    /**
     * Analyze rows aggregated data about atoms
     *
     * @param atomsData data to analyze
     */
    protected void analyzeRowsAtoms(List<ArrayListMultimap<Atom, Integer>> atomsData) {
        analyzeAtoms(atomsData);
    }

    /**
     * Analyze columns aggregated data about candidates
     *
     * @param candidatesData data to analyze
     */
    protected void analyzeColumnsCandidates(List<ArrayListMultimap<Integer, Atom>> candidatesData) {
        analyzeCandidates(candidatesData);
    }

    /**
     * Analyze columns aggregated data about atoms
     *
     * @param atomsData data to analyze
     */
    protected void analyzeColumnsAtoms(List<ArrayListMultimap<Atom, Integer>> atomsData) {
        analyzeAtoms(atomsData);
    }

    /**
     * Analyze sectors aggregated data about candidates
     *
     * @param candidatesData data to analyze
     */
    protected void analyzeSectorsCandidates(List<ArrayListMultimap<Integer, Atom>> candidatesData) {
        analyzeCandidates(candidatesData);
    }

    /**
     * Analyze sectors aggregated data about atoms
     *
     * @param atomsData data to analyze
     */
    protected void analyzeSectorsAtoms(List<ArrayListMultimap<Atom, Integer>> atomsData) {
        analyzeAtoms(atomsData);
    }

    /**
     * Analyze aggregated data about atoms
     *
     * @param atomsData data to analyze
     */
    protected void analyzeAtoms(List<ArrayListMultimap<Atom,Integer>> atomsData) {
        // do nothing
    }

    /**
     * Analyze aggregated data about candidates
     *
     * @param candidatesData data to analyze
     */
    protected void analyzeCandidates(List<ArrayListMultimap<Integer,Atom>> candidatesData) {
        // do nothing
    }


    /** data processors */

    /**
     * Processing columns and defining what atoms can have such value
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Integer, Atom>> processColumnsOnCandidateLevel(List<Atom[]> dataChunks) {
        return processDataOnCandidateLevel(dataChunks);
    }

    /**
     * Processing columns and defining what values can have each atom
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Atom, Integer>> processColumnsOnAtomLevel(List<Atom[]> dataChunks) {
        return processDataOnAtomLevel(dataChunks);
    }

    /**
     * Processing sectors and defining what atoms can have such value
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Integer, Atom>> processSectorsOnCandidateLevel(List<Atom[]> dataChunks) {
        return processDataOnCandidateLevel(dataChunks);
    }

    /**
     * Processing sectors and defining what values can have each atom
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Atom, Integer>> processSectorsOnAtomLevel(List<Atom[]> dataChunks) {
        return processDataOnAtomLevel(dataChunks);
    }

    /**
     * Processing rows and defining what atoms can have such value
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Integer, Atom>> processRowsOnCandidateLevel(List<Atom[]> dataChunks) {
        return processDataOnCandidateLevel(dataChunks);
    }

    /**
     * Processing rows and defining what values can have each atom
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Atom, Integer>> processRowsOnAtomLevel(List<Atom[]> dataChunks) {
        return processDataOnAtomLevel(dataChunks);
    }


    /**
     * Processing data chunks and defining what atoms can have such value
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Integer, Atom>> processDataOnCandidateLevel(List<Atom[]> dataChunks) {
        return Lists.newArrayList();
    }

    /**
     * Processing data chunks and defining what values can have each atom
     *
     * @param dataChunks list of columns
     * @return aggregated data
     */
    protected List<ArrayListMultimap<Atom, Integer>> processDataOnAtomLevel(List<Atom[]> dataChunks) {
        return Lists.newArrayList();
    }

    /** assemblers */

    /**
     * Assemble list of sectors from specified data
     * 
     * @param data raw date
     * @return list of sectors
     */
    protected List<Atom[]> assembleSectors(Sector[][] data) {
        List<Atom[]> sectors = Lists.newArrayList();
        int length = data.length;
        for (int x = 0; x < (length); x++) {
            for (int y = 0; y < length; y++) {
                Atom[][] atoms = data[x][y].getAtoms();
                Atom[] sector = new Atom[length * length];
                int currentPosition = 0;

                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < length; j++) {
                        sector[currentPosition] = atoms[i][j];
                        currentPosition++;
                    }
                }
                sectors.add(sector);
                log.debug("Examining " + Arrays.toString(sector));
            }
        }
        return sectors;
    }

    /**
     * Assemble list of columns from specified data
     * 
     * @param data raw date
     * @return list of columns
     */
    protected List<Atom[]> assembleColumns(Sector[][] data) {
        List<Atom[]> columns = Lists.newArrayList();
        int length = data.length;
        for (int i = 0; i < (length); i++) {
            for (int j = 0; j < length; j++) {
                Atom[] column = extractColumn(data, i, j);
                columns.add(column);
                log.debug("Examining " + Arrays.toString(column));

            }
        }
        return columns;
    }

    /**
     * Assemble list of rows from specified data
     * 
     * @param data raw date
     * @return list of rows
     */
    protected List<Atom[]> assembleRows(Sector[][] data) {
        List<Atom[]> rows = Lists.newArrayList();
        int length = data.length;
        for (int i = 0; i < (length); i++) {
            for (int j = 0; j < length; j++) {
                Atom[] row = extractRow(data, i, j);
                rows.add(row);
                log.debug("Examining " + Arrays.toString(row));

            }
        }
        return rows;
    }

    protected boolean isNumberPresent(int numberCandidate, Atom[] atoms) {
        for (Atom atom : atoms) {
            if (atom.solved() && atom.getValue() == numberCandidate) {
                return true;
            }
        }
        return false;
    }

    protected boolean isNumberPresent(int numberCandidate, Atom[][] atoms) {
        for (Atom[] atomArray : atoms) {
            if (isNumberPresent(numberCandidate, atomArray)) {
                return true;
            }
        }
        return false;
    }
}
