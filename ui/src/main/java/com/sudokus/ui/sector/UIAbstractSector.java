package com.sudokus.ui.sector;

import javax.swing.*;
import java.awt.*;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/15/12
 */
public abstract class UIAbstractSector extends JLayeredPane implements UISector {

    private Component[][] units;

    public UIAbstractSector(Object[][] data) {
        init(data);
    }

    private void init(Object[][] data) {

        Component[][] units = initUnits(data);
        GroupLayout layout = new GroupLayout(this);

        this.setUnits(units);
        this.setLayout(layout);

        createHorizontalGroup();
        createVerticalGroup();

    }

    private Component[][] initUnits(Object[][] data) {
        int size = data.length;
        Component[][] units = new Component[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                units[i][j] = createSectorUnit(data[i][j]);
            }
        }

        return units;
    }

    protected abstract Component createSectorUnit(Object value);

    protected abstract int getUnitSize();

    public Component[][] getUnits() {
        return units;
    }
    
    public void setUnits(Component[][] units) {
        this.units = units;
    }

    @Override
    public void createHorizontalGroup() {
        GroupLayout layout = (GroupLayout) this.getLayout();
        GroupLayout.ParallelGroup group = layout.createParallelGroup(GroupLayout.Alignment.LEADING);

        for (Component[] unit : getUnits()) {
            GroupLayout.SequentialGroup sequentialGroup = layout.createSequentialGroup();
            for (Component u : unit) {
                sequentialGroup.addComponent(u, GroupLayout.PREFERRED_SIZE, getUnitSize(), GroupLayout.PREFERRED_SIZE);
            }
            group.addGroup(sequentialGroup);
        }
        layout.setHorizontalGroup(group);
    }

    @Override
    public void createVerticalGroup() {
        GroupLayout layout = (GroupLayout) this.getLayout();
        GroupLayout.SequentialGroup sequentialGroup = layout.createSequentialGroup();

        for (Component[] unit : getUnits()) {
            GroupLayout.ParallelGroup parallelGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
            for (Component u : unit) {
                parallelGroup.addComponent(u, GroupLayout.PREFERRED_SIZE, getUnitSize(), GroupLayout.PREFERRED_SIZE);
            }
            sequentialGroup.addGroup(parallelGroup);
        }

        GroupLayout.ParallelGroup parallelGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.ParallelGroup parallelGroup1 = parallelGroup.addGroup(GroupLayout.Alignment.TRAILING, sequentialGroup);
        layout.setVerticalGroup(parallelGroup1);
    }
}
