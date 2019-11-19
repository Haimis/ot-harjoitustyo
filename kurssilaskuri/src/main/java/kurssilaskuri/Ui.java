
package kurssilaskuri;

import java.util.Scanner;

public class Ui {
    Scanner s;

    public Ui() {
        this.s = new Scanner(System.in);
        System.out.println("Tervetuloa käyttämään kurssilaskuria. Sovelluksella voit verrata Xtrackers Euro Stoxx 50 UCITS ETF:n avauskursseja "
                + "kuukauden viiden ensimmäisen päivän ja valitsemasi ajanjakson välillä.");
        System.out.println("Paina jotain näppäintä aloittaaksesi");
    }
    
    public void start(ETF etf) {
        while(!s.nextLine().equals("1")) {
            System.out.println("1: sulje sovellus");
            System.out.println("2: vertaa kursseja");
            String str = s.nextLine();
            if (str.equals("2")) {
                comparePrices(etf);
            } else if (str.equals("1")) {
                System.out.println("Suljetaan sovellus");
                System.out.println("Kiitos käytöstä!");
                break;
            } else {
                System.out.println("Anna kunnollinen komento");
            }
        }
    }
    
    public void comparePrices(ETF etf) {
        int startDate;
        int endDate;

        System.out.println("Anna vertailujakson aloituspäivä väliltä 1, ..., 31");
        String str = s.nextLine();
        if (validateDate(str)) {
            startDate = Integer.parseInt(str);
        } else {
            System.out.println("Anna kunnollinen komento, paina mitä tahansa näppäintä jatkaaksesi");
            return;
        }
        System.out.println("Anna vertailujakson lopetuspäivä väliltä 1, ..., 31:");
        str = s.nextLine();
        if (validateDate(str)) {
            endDate = Integer.parseInt(str);
        } else {
            System.out.println("Anna kunnollinen komento, paina mitä tahansa näppäintä jatkaaksesi");
            return;
        }   
        
        double average = 0;
        int aver = 0;
        int first = 0;
        double firstAverage = 0;
        for (DataPoint dp : etf.dataPoints) {
            if (dp.date >= startDate && dp.date <= endDate) {
                aver++;
                average = average + dp.open;
            }
            if (dp.date <= 5) {
                first++;
                firstAverage = firstAverage + dp.open;
            }
        }
        double firstPrice = firstAverage/first;
        double searchPrice = average/aver;
        double difference = (searchPrice/firstPrice-1)*100;
        String result = String.format("%.2f", difference);

        System.out.println("Datan perusteella kuukauden 1.-5. päivän keskikurssi on: " + firstPrice);
        System.out.println("Vastaavasti kuukauden " + startDate + ".-" + endDate + ". päivän keskikurssi on: " + searchPrice);
        System.out.println("Osakkeet ovat siis vertailujaksolla keskimäärin " + (result) + "% kalliimpia, kuin kuun alussa");
        System.out.println("Ero perustuu " + first + " hintatietoon kuun alusta ja " + aver + " hintatietoon valitsemallasi välillä");
        System.out.println("Paiva mitä tahansa näppäintä jatkaaksesi");
    }
    
    public boolean validateDate(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
}
