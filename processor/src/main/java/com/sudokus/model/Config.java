package com.sudokus.model;

/**
 * @author skrauchenia
 */
public final class Config {

    private Sector[][] data;

    public Config(Sector[][] data) {
        this.data = data;
    }

    public Sector[][] getData() {
        return data;
    }
}
