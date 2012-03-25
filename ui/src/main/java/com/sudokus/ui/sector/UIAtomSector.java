/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokus.ui.sector;

import com.sudokus.model.Atom;

import java.awt.*;

/**
 * @author kupal
 */
public class UIAtomSector extends UIAbstractSector implements UISector {


    public static final int DEFAULT_BUTTON_SIZE = 35;

    public UIAtomSector(Atom[][] data) {
        super(data);
    }

    @Override
    protected Component createSectorUnit(Object value) {
        return new UIAtom((Atom)value);
    }

    @Override
    protected int getUnitSize() {
        return DEFAULT_BUTTON_SIZE;
    }

}
