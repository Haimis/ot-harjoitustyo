
package kurssilaskuri.logics;

import kurssilaskuri.logics.DataPoint;
import java.util.*;


public class ETF {
    String name;
    List<DataPoint> dataPoints;

    public ETF(String name) {
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
