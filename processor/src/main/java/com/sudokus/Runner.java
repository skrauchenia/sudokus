package com.sudokus;

import com.sudokus.processor.Processor;
import com.sudokus.util.ProcessorIterator;

import java.util.Iterator;

/**
 * @author skrauchenia
 */
public class Runner {

    public static void main(String[] args) {
        Iterator<Processor> iterator = new ProcessorIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getClass());
        }

    }
    
    public static boolean getFalse() {
        System.out.println("getting false");
        return false;
    }

    public static boolean getTrue() {
        System.out.println("getting true");
        return true;
    }
}
