package com.sudokus.ui;

import com.sudokus.model.Atom;
import com.sudokus.model.Config;
import com.sudokus.model.Sector;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static com.sudokus.util.DataUtil.*;

/**
 * @author skrauchenia
 */
public class Runner {

    private static final Logger log = Logger.getLogger(Runner.class);
    private static final Properties properties = new Properties();
    private static final String DATA_SET_FILE_NAME = "classpath:data-sets.properties";
    private static final int sections_size = 3;
    private static final int atoms_size = 3;

    static {
        try {
            properties.load(Runner.class.getClassLoader().getResourceAsStream("data-sets.properties"));
        } catch (IOException e) {
            log.error("Failed to load data sets file", e);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("specify data set name. Available names: " + properties.keySet());
        }

        Config config = generateConfig(args[0]);
        MainFrame.render(config);
    }

    public static Config generateConfig(String dataSetName) {
        Sector[][] data = new Sector[3][3];
        String[] dataSet = properties.get(dataSetName).toString().split(" ");
        log.info("Initializing data...");
        log.info(String.format("Data set %s:%s", dataSetName, Arrays.toString(dataSet)));

        double currentPosition = 0;
        double resolvedCount = 0;

        for (int i = 0; i < sections_size; i++) {
            for (int j = 0; j < sections_size; j++) {
                Atom[][] atoms = new Atom[3][3];
                for (int k = 0; k < atoms_size; k++) {
                    for (int l = 0; l < atoms_size; l++) {
                        int row = i * sections_size + (k + 1);
                        int column = j * sections_size + (l + 1);
                        int index = ((row - 1) * sections_size * atoms_size) + column - 1;
                        int sectorNumber = i * sections_size + (j + 1);
                        int atomInSectorNumber = k * atoms_size + (l + 1);

                        log.debug(String.format("index=%s|sector=%s|atom=%s|row=%s|column=%s|coordinates=%s%s%s%s", index, sectorNumber, atomInSectorNumber, row, column, i, j, k, l));
                        int value = Integer.parseInt(dataSet[index]);
                        if (value == 0) {
                            atoms[k][l] = new Atom();
                        } else {
                            atoms[k][l] = new Atom(value);
                            resolvedCount++;
                        }
                        currentPosition++;
                    }
                }
                data[i][j] = new Sector(atoms);
            }

        }

        addAtomCoordinates(data);
        addSectionsToAtom(data);
        addRowToAtom(data);
        addColumnToAtom(data);

        log.info(String.format("Data initialization complete. Initially resolved - %s (%s percents)", (int)resolvedCount, (int)((resolvedCount/(currentPosition+1))*100)));
        
        return new Config(data);
    }

    private static void addRowToAtom(Sector[][] data) {
        log.debug("adding columns to atoms");
        for (Atom atom : atomsAsList(data)) {
            Atom[] row = extractRow(data, atom.getSectorX(), atom.getX());
            atom.setRow(row);
        }
    }

    private static void addAtomCoordinates(Sector[][] data) {
        log.debug("adding coordinates to atoms");
        int length = data.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int x = 0; x < length; x++) {
                    for (int y = 0; y < length; y++) {
                        Atom atom = data[i][j].getAtoms()[x][y];
                        atom.setCoordinates(i, j, x, y);
                    }
                }
            }
        }
    }

    private static void addColumnToAtom(Sector[][] data) {
        log.debug("adding rows to atoms");
        for (Atom atom : atomsAsList(data)) {
            Atom[] column = extractColumn(data, atom.getSectorY(), atom.getY());
            atom.setColumn(column);
        }
    }

    private static void addSectionsToAtom(Sector[][] data) {
        log.debug("adding sections to atoms");
        for (Sector[] sectors : data) {
            for (Sector sector : sectors) {
                for (Atom[] atoms : sector.getAtoms()) {
                    for (Atom atom : atoms) {
                        atom.setSector(sector);
                    }
                }
            }
        }
    }
}
