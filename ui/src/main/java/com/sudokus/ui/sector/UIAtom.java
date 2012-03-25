package com.sudokus.ui.sector;

import com.sudokus.model.Atom;
import com.sudokus.ui.MultiLineToolTip;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * @author skrauchenia
 */
public class UIAtom extends JToggleButton implements KeyListener, ItemListener {

    private static final Font FONT = new Font("Ubuntu", Font.PLAIN, 12);
    private static final Logger log = Logger.getLogger(UIAtom.class);

    public static final Color SOLVED_COLOR = new Color(108, 140, 213);

    private Atom atom;

    public UIAtom(Atom atom) {
        init(atom);
    }

    @Override
    public JToolTip createToolTip() {
        MultiLineToolTip tip = new MultiLineToolTip();
        tip.setComponent(this);
        return tip;
    }

    private void init(Atom atom) {
        this.atom = atom;
        this.setText(String.valueOf(atom.getValue()));
        this.setFont(FONT);
        initListeners();

        if(log.isDebugEnabled()) {
            StringBuilder tip = new StringBuilder();
            tip.append(atom.coordinatePrettyPrint());
            tip.append("\nSection: \n");
            tip.append(atom.getSector());
            tip.append("Row: \n");
            tip.append(Arrays.toString(atom.getRow()));
            tip.append("\nColumn: \n");

            for (Atom a : atom.getColumn()) {
                tip.append(a);
                tip.append("\n");
            }

            this.setToolTipText(tip.toString());
        }
    }

    @Override
    public void setText(String text) {
        byte value = text == null ? 0 : Byte.parseByte(text);
        boolean valid = value > 0;


        if(valid) {
            super.setText(text);
            this.setBackground(SOLVED_COLOR);
            this.setForeground(Color.WHITE);
        } else {
            super.setText(null);
            this.setBackground(null);
        }
    }

    private void initListeners() {
        addItemListener(this);
        addKeyListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        UIAtom item = (UIAtom) e.getItem();
        UISectorTable parent = (UISectorTable) (item).getParent().getParent();

        for (UIAtom atom : parent.uiAtomsAsList()) {
            if (atom.isSelected() && !atom.equals(item)) {
                atom.setSelected(false);
            }
        }
    }

    public Atom getAtom() {
        return atom;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        try {
            String stringValue = String.valueOf(e.getKeyChar());
            int value = Integer.valueOf(stringValue);
            if(0 < value && value < 10) {
                this.setText(stringValue);
            }
        } catch (NumberFormatException e1) {
            if(e.getKeyChar() == KeyEvent.VK_DELETE) {
                this.setText(null);
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
