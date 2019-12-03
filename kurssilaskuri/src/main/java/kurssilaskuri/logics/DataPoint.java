
package kurssilaskuri.logics;

public class DataPoint {
    int year;
    int month;
    int date;
    double open;

    public DataPoint(int year, int month, int date, double open) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.open = open;
    }

    @Override
    public String toString() {
        return "DataPoint{" + "year=" + year + ", month=" + month + ", date=" + date + ", open=" + open + '}';
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }
    
    public String toDate() {
        String longMonth = "";
        if (month < 10) {
            longMonth = "0" + month;
        } else {
            longMonth = longMonth + month;
        }
        
        String longDate = "";
        if (date < 10) {
            longDate = "0" + date;
        } else {
            longDate = longDate + date;
        }
        return (year + "-" + longMonth + "-" + longDate);
    }
    
    
    
    
    
}
