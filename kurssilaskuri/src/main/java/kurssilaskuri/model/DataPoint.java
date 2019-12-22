
package kurssilaskuri.model;

public class DataPoint {
    private final int year;
    private final int month;
    private final int date;
    private final double open;

    public DataPoint(int year, int month, int date, double open) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.open = open;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return date;
    }

    public double getOpen() {
        return open;
    }   
}
