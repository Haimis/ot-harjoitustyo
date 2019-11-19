
package kurssilaskuri;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        String fileName = "DXET.DE.csv";
        DataBuilder d = new DataBuilder(fileName);
        ETF etf = d.read(fileName);
        
        Ui ui = new Ui();
        ui.start(etf);
        

    }
}
