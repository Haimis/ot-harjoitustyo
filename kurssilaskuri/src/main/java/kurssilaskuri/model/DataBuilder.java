package kurssilaskuri.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DataBuilder {

    public  DataBuilder() {
        
    }
    
    /**
    * Reads .csv files with read() method and adds results to a list
    * 
    * @see    kurssilaskuri.model.DataBuilder#read(String)
    * 
    * @return parsed data from .csv files as Etf objects
    */
    public List<Etf> parseData() throws FileNotFoundException, IOException  {
        List<String> dataSources = Arrays.asList("DXET.DE.csv", "DX2J.DE.csv", "EUNN.DE.csv", 
                "IS3N.DE.csv", "QDVD.DE.csv", "SXR1.DE.csv", "SXR8.DE.csv", "XCS6.DE.csv", 
                "XDJP.DE.csv", "XDUK.DE.csv");
        List<Etf> courseData = new ArrayList<>();

        dataSources.forEach((str) -> {
            try {
                courseData.add(read(str));
            } catch (IOException ex) {
                System.out.println("Tiedostoa " + str + " ei voitu lukea");
            }
        });

        return courseData;        
    }
    
    /**
    * 
    * 
    * @param   syote   Käyttäjän antama syöte
    * 
    * @return parsed data from .csv files as Etf objects
    */
    public Etf read(String fileName) throws IOException {

        BufferedReader csvReader = readerConstructor(fileName);  
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
    
    public BufferedReader readerConstructor(String fileName) throws FileNotFoundException {
        
        String path = ("src/main/resources/data/" + fileName);
        InputStream is = new FileInputStream(path);   
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);

    }
    
}
