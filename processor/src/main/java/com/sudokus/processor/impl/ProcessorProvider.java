package com.sudokus.processor.impl;

import com.sudokus.processor.Processor;

import java.util.Arrays;
import java.util.List;

/**
 * @author skrauchenia
 */
public class ProcessorProvider {

    public static List<Processor> getProcessors() {
        return Arrays.asList(new Processor[]{
                new LinearColumnProcessor(),
                new LinearRowsProcessor(),
                new LinearSectorProcessor()
        });
    }
}
