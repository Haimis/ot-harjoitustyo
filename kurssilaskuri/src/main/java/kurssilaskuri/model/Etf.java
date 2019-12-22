
package kurssilaskuri.model;

import java.util.*;


public class Etf {
    private final String name;
    private List<DataPoint> dataPoints;

    public Etf(String name) {
        this.name = name;
        this.dataPoints = new ArrayList<>();
        
    }

    public String getName() {
        return name;
    }

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }
}
