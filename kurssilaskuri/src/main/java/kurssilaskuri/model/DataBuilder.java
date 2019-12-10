package kurssilaskuri.model;

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
    
    public List<Etf> parseData() throws FileNotFoundException, IOException  {
        List<String> dataSources = Arrays.asList("DXET.DE.csv", "DX2J.DE.csv", "EUNN.DE.csv", 
                "IS3N.DE.csv", "QDVD.DE.csv", "SXR1.DE.csv", "SXR8.DE.csv", "XCS6.DE.csv", 
                "XDJP.DE.csv", "XDUK.DE.csv");
        List<Etf> courseData = new ArrayList<>();

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
    
    
    public Etf read(String fileName) throws IOException {
        
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/" + fileName));  
        String line = "";

        Etf etf = new Etf(fileName);

        while ((line = csvReader.readLine()) != null) {
            String[] dataEntry = line.split("[-,]");
            
            if (!dataEntry[0].equals("Date") && !dataEntry[3].equals("null")) {             
                DataPoint dp = new DataPoint(Integer.parseInt(dataEntry[0]), Integer.parseInt(dataEntry[1]), 
                        Integer.parseInt(dataEntry[2]), Float.parseFloat(dataEntry[3]));
                etf.getDataPoints().add(dp);
            }            
        }
        return etf;
    }
    
}
