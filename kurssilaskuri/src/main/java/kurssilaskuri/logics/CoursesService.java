
package kurssilaskuri.logics;

import java.io.IOException;
import java.util.*;

public class CoursesService {
    List<ETF> list;
    
    public CoursesService() throws IOException {
        DataBuilder d = new DataBuilder();
        this.list = d.parseData();
    }
    
    public String getAverage(int startDate, int endDate) {
        double average = 0;
        int aver = 0;
        int first = 0;
        double firstAverage = 0;
        for (DataPoint dp : this.list.get(0).getDataPoints()) {
            if (dp.getDate() >= startDate && dp.getDate() <= endDate) {
                aver++;
                average = average + dp.getOpen();
            }
            if (dp.getDate() <= 5) {
                first++;
                firstAverage = firstAverage + dp.getOpen();
            }
        }
        double firstPrice = firstAverage / first;
        double searchPrice = average / aver;
        double difference = (searchPrice / firstPrice - 1) * 100;
        String firstResult = String.format("%.2f", firstPrice);
        String searchResult = String.format("%.2f", searchPrice);
        String result = String.format("%.2f", difference);
        
        return ("Ajanjaksolla kuun " + startDate + ". - " + endDate + ". päivä osakkeiden keskiavaushinta oli " + searchResult + ". \n"
                + "Ajanjaksolla kuun 1.-5. päivä osakkeiden keskiavaushinta oli " + firstResult + ". \n"
                + "Osakkeet olivat siis vertailujaksolla " + result + "% kalliimpia kuin kuun alussa.");
    }
}
