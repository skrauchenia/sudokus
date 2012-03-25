package com.sudokus.ui.sector;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/15/12
 */
public class UISectorTable extends UIAbstractSector implements UISector {

    private static final Logger log = Logger.getLogger(UISectorTable.class);

    public UISectorTable(Sector[][] data) {
        super(data);
    }

    @Override
    protected Component createSectorUnit(Object sector) {
        return new UIAtomSector(((Sector) sector).getAtoms());
    }

    @Override
    protected int getUnitSize() {
        return 3 * (UIAtomSector.DEFAULT_BUTTON_SIZE);
    }

    public List<UIAtom> uiAtomsAsList() {
        List<UIAtom> result = new ArrayList<UIAtom>();

        for (Component[] row : super.getUnits()) {
            for (Component unit : row) {
                for (Component[] atoms : ((UIAbstractSector) unit).getUnits()) {
                    for (Component atom : atoms) {
                        result.add((UIAtom) atom);
                    }
                }
            }
        }

        return result;
    }

    public UIAtom getUIAtom(Atom atom) {
        for (UIAtom uiAtom : uiAtomsAsList()) {
            if (uiAtom.getAtom().equals(atom)) {
                return uiAtom;
            }
        }

        return null;
    }


    public void updateAtom(Atom updatedAtom) {
        UIAtom uiAtom = getUIAtom(updatedAtom);
        if (uiAtom != null) {
            uiAtom.setText(String.valueOf(updatedAtom.getValue()));
            log.debug("Value updated. Atom: " + updatedAtom.coordinatePrettyPrint() + ". Value: " + updatedAtom.getValue());
        } else {
            log.error("Failed to update atom value: " + updatedAtom);
        }
    }

    public List<Atom> atomsAsList() {
        java.util.List<Atom> result = new ArrayList<>();

        for (Component[] row : super.getUnits()) {
            for (Component unit : row) {
                for (Component[] atoms : ((UIAbstractSector) unit).getUnits()) {
                    for (Component atom : atoms) {
                        result.add(((UIAtom) atom).getAtom());
                    }
                }
            }
        }

        return result;

    }
}
