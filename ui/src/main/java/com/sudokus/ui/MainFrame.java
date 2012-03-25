package com.sudokus.ui;

import com.sudokus.model.Atom;
import com.sudokus.model.Config;
import com.sudokus.processor.AtomStorageListener;
import com.sudokus.processor.ProcessorRunner;
import com.sudokus.processor.ResolvedAtomsStorage;
import com.sudokus.ui.sector.UISectorTable;
import com.sudokus.util.DataUtil;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/14/12
 */
public class MainFrame extends JFrame {

    private static final Logger log = Logger.getLogger(MainFrame.class);
    private Config config;
    private UISectorTable sectorTable;
    private JButton startButton;
    private JButton stopButton;

    public MainFrame(Config config) {
        this.config = config;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        log.info("Start Rendering...");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setAlwaysOnTop(true);

        UISectorTable sectorTable = new UISectorTable(config.getData());
        Container contentPane = this.getContentPane();

        startButton = createStartButton();
        stopButton = createStopButton();
        JProgressBar progressBar = createProgressBar();

        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(sectorTable).addComponent(startButton).addComponent(stopButton).addComponent(progressBar));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(sectorTable).addComponent(startButton).addComponent(stopButton).addComponent(progressBar));

        pack();
        this.setBounds(500, 0, sectorTable.getWidth() + 200, sectorTable.getHeight() + 35);
        this.sectorTable = sectorTable;
        ResolvedAtomsStorage.storage().addOnPutListener(new OnAtomResolvedListener());
        log.info("Rendering done");
    }

    private JButton createStopButton() {
        return createButton(new StopButtonListener(), "Stop");
    }

    private JButton createStartButton() {
        return createButton(new StartButtonListener(), "Start");
    }

    private JButton createButton(ActionListener listener, String name) {
        JButton buttonStart = new JButton();
        buttonStart.setFont(new Font("Ubuntu", Font.PLAIN, 11));
        buttonStart.setText(name);
        buttonStart.addActionListener(listener);
        return buttonStart;
    }

    private JProgressBar createProgressBar() {
        int length = config.getData().length;
        int solved = 0;

        for (Atom atom : DataUtil.atomsAsList(config.getData())) {
            if (atom.solved()) {
                solved++;
            }
        }

        JProgressBar progressBar = new JProgressBar(0, (int) Math.pow(length * length, 2));
        progressBar.setValue(solved);
        progressBar.setStringPainted(true);

        return progressBar;
    }

    void updateProgressBar(Atom updatedAtom) {
        try {
            if (updatedAtom == null) {
                return;
            }

            JProgressBar progressBar = (JProgressBar) sectorTable.getParent().getComponents()[3];
            progressBar.setValue(progressBar.getValue() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateValue(Atom updatedAtom) {
        if (updatedAtom != null) {
            sectorTable.updateAtom(updatedAtom);
        }
    }

    public static void render(final Config config) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            log.error(ex);
        }

        /*
        * Create and display the form
        */
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame(config).setVisible(true);
            }
        });
    }

    private class OnAtomResolvedListener implements AtomStorageListener {

        @Override
        public void onAction(Atom atom) {
            updateProgressBar(atom);
            updateValue(atom);
        }
    }

    private class StartButtonListener implements ActionListener {

        private final Logger log = Logger.getLogger(ProcessorRunner.class);

        @Override
        public void actionPerformed(ActionEvent e) {

            startButton.setEnabled(false);
            startProcess();
            log.info("Processing started");
        }

        public void startProcess() {
            ProcessorRunner.startProcess(config.getData());
        }
    }

    private class StopButtonListener implements ActionListener {

        private final Logger log = Logger.getLogger(ProcessorRunner.class);

        @Override
        public void actionPerformed(ActionEvent e) {

            startButton.setEnabled(true);
            ProcessorRunner.stopProcess();
            log.info("Processing stopped");
        }
    }
}
