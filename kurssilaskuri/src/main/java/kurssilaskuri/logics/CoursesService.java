
package kurssilaskuri.logics;

import kurssilaskuri.model.DataPoint;
import kurssilaskuri.model.Etf;
import kurssilaskuri.model.DataBuilder;
import java.io.IOException;
import java.util.*;

public class CoursesService {
    List<Etf> list;
    
    public String infoBox() {
        return "Tervetuloa käyttämään kurssilaskuria!\n\nLaskuria voit käyttää kuukauden sisällä toteutuvien arbitraasien etsimiseen eri ETF-rahastoissa. "
                + "Käytä laskuria seuraavasti:\n\n1.Valitse haluamasi vertailujaksot liukuvalikoista\n2. Valitse haluamasi ETF-rahastot listasta\n3. Paina laske\n\n"
                + "Ohjelma etsii valittujen rahastojen avaushintoja kummankin ajanjakson sisällä\n(esim. kuukauden 1.-5. päivä ja kuukauden 7.-8. päivä) ja vertaa"
                + " niitä toisiinsa. \n\nVertailu toteutetaan aina kuukausittain. Mikäli pörssi on ollut jossain kuussa suljettu koko toisen vertailujakson ajan ei kyseisen "
                + "kuukauden hintoja huomioida myöskään toiselta vertailujaksolta. Tällä ehkäistään vääristymää, joka syntyy osakkeiden kallistumisesta ajan myötä. "
                + "Mikäli toisen vertailujakson havainnot sijoittuvat keskimäärin lähemmäs nykypäivää, näyttäytyy kyseinen vertailujakso todellista kalliimpana.\n";
    }
    
    public CoursesService() throws IOException {
        DataBuilder d = new DataBuilder();
        this.list = d.parseData();
    }
    
    public String generateComparison(List<String> lista, int startDate, int endDate, int startDate2, int endDate2) {
        ArrayList<Integer> etfNum = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (lista.contains(list.get(i).getName())) {
                etfNum.add(i);
            }
        }
        Double monthlyDifferences = 0.0;
        String ret = "";
        for (int i : etfNum) {
            monthlyDifferences =+ monthlyDifference(i, startDate, endDate, startDate2, endDate2);
        }
        double diff = monthlyDifferences/etfNum.size();
        if (diff < 0) {
            return "Valitsemasi rahastot olivat keskimäärin " + String.format("%.6f", Math.abs(diff)) + "% kalliimpia\n 1. vertailujakson kuin 2. vertailujakson aikana.";    
        }
        return "Valitsemasi rahastot olivat keskimäärin " + String.format("%.6f", diff) + "% halvempia\n 1. vertailujakson kuin 2. vertailujakson aikana.";    
        
    }
    
    //Not in use right now
    /*
    public String getAverage(int etfNum, int startDate, int endDate) {
        double average = 0;
        int aver = 0;
        int first = 0;
        double firstAverage = 0;
        for (DataPoint dp : this.list.get(etfNum).getDataPoints()) {
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
        
        
        //System.out.println(diff);
        double diff = 0;
        String result2 = String.format(("%.4f"), diff);
        
        return ("Ajanjaksolla kuun " + startDate + ". - " + endDate + ". päivä osakkeiden keskiavaushinta oli " + searchResult + ". \n"
                + "Ajanjaksolla kuun 1.-5. päivä osakkeiden keskiavaushinta oli " + firstResult + ". \n"
                + "Osakkeet olivat siis vertailujaksolla " + result2 + "% kalliimpia kuin kuun alussa.");
//        return ("Ajanjaksolla kuun " + startDate + ". - " + endDate + ". päivä osakkeiden keskiavaushinta oli " + searchResult + ". \n"
//                + "Ajanjaksolla kuun 1.-5. päivä osakkeiden keskiavaushinta oli " + firstResult + ". \n"
//                + "Osakkeet olivat siis vertailujaksolla " + result + "% kalliimpia kuin kuun alussa.");
    }*/
    
    // monthly average and their difference
    public Double monthlyDifference(int etfNum, int startDate, int endDate, int startDate2, int endDate2) {
        int startYear = this.list.get(etfNum).getDataPoints().get(0).getYear();
        int startMonth = this.list.get(etfNum).getDataPoints().get(0).getMonth();
        double beg = 0;
        double chosen = 0;
        int begInt = 0;
        int chosenInt = 0;
        
        ArrayList<Double> differences = new ArrayList<>();
        
        for (DataPoint dp : this.list.get(etfNum).getDataPoints()) {
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
                if (dp.getDate() >= startDate && dp.getDate() <= endDate) {
                    beg = beg + dp.getOpen();
                    begInt++;
                }
                
                if (dp.getDate() >= startDate2 && dp.getDate() <= endDate2) {
                    chosen = chosen + dp.getOpen();
                    chosenInt++;
                }
            }
            
            
        }
        
        ArrayList<Double> data = differences;
        double sum = 0;
        for (double d : data) {
            sum = sum + d;
        }
        double diff = sum / data.size();
        
        
        return diff;
    }

    public List<Etf> getList() {
        return list;
    }
}
