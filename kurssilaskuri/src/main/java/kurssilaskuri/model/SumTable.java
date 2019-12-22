
package kurssilaskuri.model;

public class SumTable {
    private double firstSum;
    private int firstCounter;
    private double secondSum;
    private int secondCounter;

    public SumTable() {
        this.initializeTable();
    }
    
    
    

    /**
     * Initializes SumTable that keeps track of monthly open values for both
     * review periods to zero.
    */
    public void initializeTable() {
        this.firstSum = 0;
        this.firstCounter = 0;
        this.secondSum = 0;
        this.secondCounter = 0;
    }

    public void setFirstSum(double firstSum) {
        this.firstSum = firstSum;
    }

    public void setFirstCounter(int firstCounter) {
        this.firstCounter = firstCounter;
    }

    public void setSecondSum(double secondSum) {
        this.secondSum = secondSum;
    }

    public void setSecondCounter(int secondCounter) {
        this.secondCounter = secondCounter;
    }
    
    public double getFirstSum() {
        return firstSum;
    }

    public int getFirstCounter() {
        return firstCounter;
    }

    public double getSecondSum() {
        return secondSum;
    }

    public int getSecondCounter() {
        return secondCounter;
    }
    
    
    
    
    
}
