package kurssilaskuri.logics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBuilder { 

    public  DataBuilder() {
        
    }
    
    public List<ETF> parseData() throws FileNotFoundException, IOException  {
        List<String> dataSources = Arrays.asList("DXET.DE.csv",  "SXR8.DE.csv"/*, " XDUK.DE.csv"*/);
        List<ETF> courseData = new ArrayList<>();

        dataSources.forEach((str) -> {
            try {
                courseData.add(read(str));
            } catch (IOException ex) {
                Logger.getLogger(DataBuilder.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Tiedostoa " + str + " ei voitu lukea");
            }
        });

        return courseData;        
    }
    
    
    public ETF read(String fileName) throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader("data/" + fileName));  
        String line = "";

        ETF etf = new ETF(fileName);

        while ((line = csvReader.readLine()) != null) {
            String[] dataEntry = line.split("[-,]");
            
            if (!dataEntry[0].equals("Date") && !dataEntry[3].equals("null")) {
                
                DataPoint dp = new DataPoint(Integer.parseInt(dataEntry[0]), Integer.parseInt(dataEntry[1]), 
                        Integer.parseInt(dataEntry[2]), Float.parseFloat(dataEntry[3]));
                etf.getDataPoints().add(dp);

                //System.out.println(dp.toString());
            }
            
        }
//        System.out.println("luotu: " + etf.name);
        
        return etf;
    }
    
}
