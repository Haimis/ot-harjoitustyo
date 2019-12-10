
package kurssilaskuri.model;

import kurssilaskuri.model.DataPoint;
import java.util.*;


public class Etf {
    String name;
    List<DataPoint> dataPoints;

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
