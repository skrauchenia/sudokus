package com.sudokus.util;

import com.sudokus.processor.Processor;
import com.sudokus.processor.impl.ProcessorProvider;

import java.util.Iterator;
import java.util.List;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/25/12
 */
public class ProcessorIterator implements Iterator<Processor> {

    private List<Processor> data;
    private int currentPosition;
    private int lastElementIndex;
    private int firstElementIndex;
    
    public ProcessorIterator() {
        init(ProcessorProvider.getProcessors());
    }

    private void init(List<Processor> data) {
        this.data = data;
        this.currentPosition = -1;
        this.firstElementIndex = 0;
        this.lastElementIndex = data.size() - 1;
    }

    public boolean hasNext() {
        return true;
    }

    public Processor next() {
        return data.get(this.getNextIndex());
    }
    
    private int getNextIndex() {
        incrementCurrentPosition();
        
        return currentPosition;
    }

    private void incrementCurrentPosition() {
        this.currentPosition = (currentPosition == lastElementIndex) ? firstElementIndex : currentPosition+1;
    }

    public void remove() {
        // do nothing
    }
}
