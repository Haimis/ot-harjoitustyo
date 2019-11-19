
package kurssilaskuri;

public class DataPoint {
    int year;
    int month;
    int date;
    float open;

    public DataPoint(int year, int month, int date, float open) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.open = open;
    }

    @Override
    public String toString() {
        return "DataPoint{" + "year=" + year + ", month=" + month + ", date=" + date + ", open=" + open + '}';
    }
    
    
    
}
