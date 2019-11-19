package kurssilaskuri;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DataBuilder {
    BufferedReader csvReader;
    String line = "";

    public DataBuilder(String fileName) throws FileNotFoundException, IOException  {
        this.csvReader = new BufferedReader(new FileReader("data/"+fileName));
    }
    
    public ETF read(String fileName) throws IOException {
        ETF etf = new ETF(fileName);
        
        while ((line = csvReader.readLine()) != null) {
            String[] dataEntry = line.split("[-,]");
            if (!dataEntry[0].equals("Date") && !dataEntry[3].equals("null")) {
                DataPoint dp = new DataPoint(Integer.parseInt(dataEntry[0]), Integer.parseInt(dataEntry[1]), 
                        Integer.parseInt(dataEntry[2]), Float.parseFloat(dataEntry[3]));
            etf.dataPoints.add(dp);
                // System.out.println(dp.toString());
            }
            
        }
        
        return etf;
    }
    
}
