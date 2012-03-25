package com.sudokus.ui;

import com.sudokus.model.Atom;
import com.sudokus.model.Config;
import com.sudokus.model.Sector;
import org.apache.log4j.Logger;

import static com.sudokus.util.DataUtil.*;

/**
 * @author skrauchenia
 */
public class Runner {

    private static final Logger log = Logger.getLogger(Runner.class);
    
    public static void main(String[] args) {
        Config config = generateConfig();
        MainFrame.render(config);
    }

    public static Config generateConfig() {
        Sector[][] data = new Sector[3][3];

        log.info("Initializing data...");
        data[0][0] = new Sector(new Atom[][]{
                {new Atom(), new Atom(8), new Atom()},
                {new Atom(), new Atom(), new Atom()},
                {new Atom(4), new Atom(), new Atom()}});
        data[0][1] = new Sector(new Atom[][]{
                {new Atom(1), new Atom(), new Atom(4)},
                {new Atom(), new Atom(6), new Atom(3)},
                {new Atom(), new Atom(), new Atom(9)}});
        data[0][2] = new Sector(new Atom[][]{
                {new Atom(), new Atom(), new Atom()},
                {new Atom(4), new Atom(1), new Atom()},
                {new Atom(), new Atom(), new Atom(8)}});

        data[1][0] = new Sector(new Atom[][]{
                {new Atom(), new Atom(1), new Atom()},
                {new Atom(9), new Atom(6), new Atom()},
                {new Atom(), new Atom(), new Atom()}});
        data[1][1] = new Sector(new Atom[][]{
                {new Atom(9), new Atom(), new Atom()},
                {new Atom(4), new Atom(), new Atom()},
                {new Atom(5), new Atom(), new Atom()}});
        data[1][2] = new Sector(new Atom[][]{
                {new Atom(8), new Atom(), new Atom()},
                {new Atom(), new Atom(5), new Atom(1)},
                {new Atom(), new Atom(7), new Atom(6)}});

        data[2][0] = new Sector(new Atom[][]{
                {new Atom(), new Atom(), new Atom()},
                {new Atom(), new Atom(), new Atom()},
                {new Atom(7), new Atom(), new Atom()}});
        data[2][1] = new Sector(new Atom[][]{
                {new Atom(3), new Atom(), new Atom()},
                {new Atom(), new Atom(7), new Atom()},
                {new Atom(), new Atom(), new Atom()}});
        data[2][2] = new Sector(new Atom[][]{
                {new Atom(), new Atom(), new Atom()},
                {new Atom(), new Atom(6), new Atom()},
                {new Atom(), new Atom(3), new Atom()}});

        addAtomCoordinates(data);
        addSectionsToAtom(data);
        addRowToAtom(data);
        addColumnToAtom(data);

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
