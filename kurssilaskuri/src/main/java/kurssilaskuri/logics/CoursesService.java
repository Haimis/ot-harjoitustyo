
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
        String firstResult = String.format("%.4f", firstPrice);
        String searchResult = String.format("%.4f", searchPrice);
        String result = String.format("%.4f", difference);
        
        
        //monthly average and their difference method
        ArrayList<Double> data = monthlyDifference(startDate, endDate);
        double sum = 0;
        for (double d : data) {
            sum = sum + d;
        }
        double diff = sum / data.size();
        //System.out.println(diff);
        String result2 = String.format(("%.4f"), diff);
        
        return ("Ajanjaksolla kuun " + startDate + ". - " + endDate + ". päivä osakkeiden keskiavaushinta oli " + searchResult + ". \n"
                + "Ajanjaksolla kuun 1.-5. päivä osakkeiden keskiavaushinta oli " + firstResult + ". \n"
                + "Osakkeet olivat siis vertailujaksolla " + result2 + "% kalliimpia kuin kuun alussa.");
//        return ("Ajanjaksolla kuun " + startDate + ". - " + endDate + ". päivä osakkeiden keskiavaushinta oli " + searchResult + ". \n"
//                + "Ajanjaksolla kuun 1.-5. päivä osakkeiden keskiavaushinta oli " + firstResult + ". \n"
//                + "Osakkeet olivat siis vertailujaksolla " + result + "% kalliimpia kuin kuun alussa.");
    }
    
    // monthly average and their difference
    public ArrayList<Double> monthlyDifference(int startDate, int endDate) {
        int startYear = this.list.get(0).getDataPoints().get(0).getYear();
        int startMonth = this.list.get(0).getDataPoints().get(0).getMonth();
        double beg = 0;
        double chosen = 0;
        int begInt = 0;
        int chosenInt = 0;
        
        ArrayList<Double> differences = new ArrayList<>();
        
        for (DataPoint dp : this.list.get(0).getDataPoints()) {
            if (dp.getMonth() != startMonth) {
                if (begInt == 0 || chosenInt == 0) {
                    differences.add(0.0);
                    begInt = 0;
                    chosenInt = 0;
                    beg = 0;
                    chosen = 0;
                } else {
                    differences.add(((chosen / chosenInt) / (beg / begInt)) - 1);
                    begInt = 0;
                    chosenInt = 0;
                    beg = 0;
                    chosen = 0;
                }
                startYear = dp.getYear();
                startMonth = dp.getMonth();
                
            
            } else if (dp.getYear() == startYear && dp.getMonth() == startMonth) {
                if (dp.getDate() <= 5) {
                    beg = beg + dp.getOpen();
                    begInt++;
                }
                
                if (dp.getDate() >= startDate && dp.getDate() <= endDate) {
                    chosen = chosen + dp.getOpen();
                    chosenInt++;
                }
            }
            
            
        }
        
        
        return differences;
    }

    public List<ETF> getList() {
        return list;
    }
    
    
    
    
}
