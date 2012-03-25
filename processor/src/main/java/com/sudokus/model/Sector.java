package com.sudokus.model;

/**
 * @author skrauchenia
 */
public class Sector {
    
    private Atom[][] atoms;

    public Sector(Atom[][] atoms) {
        this.atoms = atoms;
    }

    public Atom[][] getAtoms() {
        return atoms;
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Atom[] atom : atoms) {
            for (int i = 0, atomLength = atom.length; i < atomLength; i++) {
                Atom a = atom[i];
                result.append(a);
                if (i < atomLength - 1) {
                    result.append("-");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }
}
