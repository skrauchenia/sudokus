package com.sudokus.processor;

import com.sudokus.model.Atom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Siarhei Krauchenia
 *         Date: 3/18/12
 */
public final class ResolvedAtomsStorage {

    private static final ResolvedAtomsStorage instance = new ResolvedAtomsStorage();

    private List<AtomStorageListener> onPut = new ArrayList<>(0);
    private List<AtomStorageListener> onPoll = new ArrayList<>(0);

    private ResolvedAtomsStorage() {}

    public void put(Atom atom) throws InterruptedException {
        instance.processListeners(onPut, atom);
    }

    public void add(Atom atom) {
        instance.processListeners(onPut, atom);
    }

    public static ResolvedAtomsStorage storage() {
        return instance;
    }

    public void addOnPutListener(AtomStorageListener listener) {
        instance.onPut.add(listener);
    }

    public void addOnPollListener(AtomStorageListener listener) {
        instance.onPoll.add(listener);
    }
    
    protected void processListeners(List<AtomStorageListener> listeners, Atom atom) {
        for (AtomStorageListener listener : listeners) {
            listener.onAction(atom);
        }
    }
}
