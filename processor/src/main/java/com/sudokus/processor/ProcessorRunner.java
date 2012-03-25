package com.sudokus.processor;

import com.sudokus.model.Atom;
import com.sudokus.model.Sector;
import com.sudokus.processor.impl.ProcessorProvider;
import com.sudokus.util.ProcessorIterator;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

/**
 * @author skrauchenia
 */
public class ProcessorRunner {

    private static final Logger log = Logger.getLogger(ProcessorRunner.class);
    private static final Iterator<Processor> iterator = new ProcessorIterator();
    private static boolean keepGoing = true;

    public static void startProcess(Sector[][] data) {
        new ProcessorRunnerThread(data).start();
    }

    public static void stopProcess() {
        keepGoing = false;
    }

    private static class ProcessorRunnerThread extends Thread {

        private Sector[][] data;

        public ProcessorRunnerThread(Sector[][] data) {
            this.data = data;
        }

        @Override
        public void run() {
            do {
                iterator.next().process(data);
            } while (keepGoing);
        }
    }
}
