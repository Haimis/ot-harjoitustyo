
package kurssilaskuri.logics;

import kurssilaskuri.model.SumTable;
import kurssilaskuri.model.DataPoint;
import kurssilaskuri.model.Etf;
import kurssilaskuri.model.DataBuilder;
import java.io.IOException;
import java.util.*;

public class CoursesService {
    private final List<Etf> list;
    
    public CoursesService() throws IOException {
        DataBuilder d = new DataBuilder();
        this.list = d.parseData();
    }
    
    /**
    * Return the info text that is used in ui
    * 
    * @return info text for the ui as String
    */
    public String infoBox() {
        return "Tervetuloa käyttämään kurssilaskuria!\n\nLaskuria voit käyttää kuukauden sisällä toteutuvien arbitraasien etsimiseen eri ETF-rahastoissa. "
                + "Käytä laskuria seuraavasti:\n\n1.Valitse haluamasi vertailujaksot liukuvalikoista\n2. Valitse haluamasi ETF-rahastot listasta\n3. Paina laske\n\n"
                + "Ohjelma etsii valittujen rahastojen avaushintoja kummankin ajanjakson sisällä\n(esim. kuukauden 1.-5. päivä ja kuukauden 7.-8. päivä) ja vertaa"
                + " niitä toisiinsa. \n\nVertailu toteutetaan aina kuukausittain. Mikäli pörssi on ollut jossain kuussa suljettu koko toisen vertailujakson ajan ei kyseisen "
                + "kuukauden hintoja huomioida myöskään toiselta vertailujaksolta. Tällä ehkäistään vääristymää, joka syntyy osakkeiden kallistumisesta ajan myötä. "
                + "Mikäli toisen vertailujakson havainnot sijoittuvat keskimäärin lähemmäs nykypäivää, näyttäytyy kyseinen vertailujakso todellista kalliimpana.\n";
    }
    
    
    /**
    * Receives parameters for comparison, passes data to other methods and
    * returns comparison result as a string
    * 
    * @param   etfNumListAsString list of files chosen by checkbox in ui
    * @param   firstStartDay start day of first slider
    * @param   firstEndDay end day of first slider
    * @param   secondStartDay start day of second slider
    * @param   secondEndDay end day of second slider
    * 
    * 
    * @see kurssilaskuri.logic.CourseSevice#includeindexes(String)
    * @see kurssilaskuri.logic.CourseSevice#monthlyDifference(Integer, Integer, Integer, Integer, Integer)
    * @see kurssilaskuri.logic.CourseSevice#analyseMonth(SumTable)
    * @see kurssilaskuri.logic.CourseSevice#updateSumtable(SumTable, DataPoint, Integer, Integer, Integer, Integer)
    * @see kurssilaskuri.logic.CourseSevice#checkIfCorrectTimeframe(DataPoint, Integer, Integer)
    * @see kurssilaskuri.logic.CourseSevice#averageOfDifferences(ArrayList<Double>)
    * 
    * 
    * @return comparison results
    */
    public String generateComparison(List<String> etfNumListAsString, int firstStartDay, int firstEndDay, int secondStartDay, int secondEndDay) {
        ArrayList<Integer> etfNumbers = includeIndexes(etfNumListAsString);
        Double monthlyDifferences = 0.0;
        
        for (int i : etfNumbers) {
            monthlyDifferences = monthlyDifferences + monthlyDifference(i, firstStartDay, firstEndDay, secondStartDay, secondEndDay);
        }
        double diff = monthlyDifferences / etfNumbers.size();
        String result;
        if (diff < 0) {
            result = (String.format("%.6f", Math.abs(diff)) + "% kalliimpia");
        } else {
            result = (String.format("%.6f", diff) + "% halvempia");
        }
        return "Valitsemasi rahastot olivat keskimäärin " + result + "\n 1. vertailujakson kuin 2. vertailujakson aikana.";        
    }
    
    /**
    * Receives list of Etf names that should be compared and returns their
    * indexis in Etf list
    * 
    * @param   etfNumListAsString list of files chosen by checkbox in ui
    * 
    * @return List indexes that should be used in comparison
    */
    public ArrayList<Integer> includeIndexes(List<String> etfNumListAsString) {
        ArrayList<Integer> etfNumbers = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (etfNumListAsString.contains(list.get(i).getName())) {
                etfNumbers.add(i);
            }
        }   
        return etfNumbers;
    }
    
    /**
    * Compares a single Etf monthly on given day periods and returnes average 
    * of monthly differences
    * 
    * @param   etfNum index of Etf that should be compared
    * @param   firstStartDay start day of first slider
    * @param   firstEndDay end day of first slider
    * @param   secondStartDay start day of second slider
    * @param   secondEndDay end day of second slider
    * 
    * @return Average of monthly comparisons between two given time frames
    */    
    public Double monthlyDifference(int etfNum, int firstStartDay, int firstEndDay, int secondStartDay, int secondEndDay) {
        int year = this.list.get(etfNum).getDataPoints().get(0).getYear();
        int month = this.list.get(etfNum).getDataPoints().get(0).getMonth();
        SumTable st = new SumTable();
        ArrayList<Double> differences = new ArrayList<>();
        
        for (DataPoint dp : this.list.get(etfNum).getDataPoints()) {
            if (dp.getMonth() != month) {
                double d = analyseMonth(st);
                if (d != 0) {
                    differences.add(d);
                }
                year = dp.getYear();
                month = dp.getMonth();
            } else if (dp.getYear() == year && dp.getMonth() == month) {
                updateSumtable(st, dp, firstStartDay, firstEndDay, secondStartDay, secondEndDay);
            }
        }
        return averageOfDifferences(differences);
    }
    
    /**
    * Receives applicable observations from single month and return %-difference
    * 
     * @param st SumTable of applicable observations within single month
    * 
    * @return %-difference between 1. and 2. time period price averages within
    * one month. Returns 0 if no observations from one time period
    */
    public double analyseMonth(SumTable st) {
        if (st.getFirstCounter() == 0 || st.getSecondCounter() == 0) {
            st.initializeTable();
            return 0;
        }
        double d = ((st.getSecondSum() / st.getSecondCounter()) / (st.getFirstSum() / st.getFirstCounter()) - 1);
        st.initializeTable();
        return d;       
    }

    /**
    * Evaluates single datapoint and updates SumTable if observations are applicable
    * 
    * @param   st SumTable of observations of month that's beeing evaluated
    * @param   dp DataPoint that's being evaluated
    * @param   firstStartDay start day of first slider
    * @param   firstEndDay end day of first slider
    * @param   secondStartDay start day of second slider
    * @param   secondEndDay end day of second slider
    * 
    */    
    public void updateSumtable(SumTable st, DataPoint dp, int firstStartDay, int firstEndDay, int secondStartDay, int secondEndDay) {
        if (checkIfCorrectTimeframe(dp, firstStartDay, firstEndDay)) {
            st.setFirstSum(st.getFirstSum() + dp.getOpen());
            st.setFirstCounter(st.getFirstCounter() + 1);
        }
        if (checkIfCorrectTimeframe(dp, secondStartDay, secondEndDay) == true) {
            st.setSecondSum(st.getSecondSum() + dp.getOpen());
            st.setSecondCounter(st.getSecondCounter() + 1);
        }
    }
    
    
    /**
    * checks if DataPoint is a legit observation for given time frame
    * 
    * @param   dp DataPoint that's beeing evaluated
    * @param   start first day of time frame
    * @param   end last day of time frame
    * 
    * @return true if DataPoint is within time frame. False if not
    */    
    public boolean checkIfCorrectTimeframe(DataPoint dp, int start, int end) {
        if (dp.getDay() >= start && dp.getDay() <= end) {
            return true;
        }
        return false;
    }
    
    /**
    * Receives a list of all monthly differences of given Etf. Returns average
    * of differences
    * 
    * @param   differences list of monthly %-differences in prices between two
    * given time frames
    * 
    * @return average of monthly %-differences
    */        
    public double averageOfDifferences(ArrayList<Double> differences) {
        double sum = 0;
        for (double d : differences) {
            sum = sum + d;
        }
        double diff = sum / differences.size();
        return diff;
        
    }

    public List<Etf> getList() {
        return list;
    }
}
