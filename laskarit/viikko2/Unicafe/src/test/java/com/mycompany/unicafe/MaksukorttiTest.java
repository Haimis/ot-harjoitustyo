package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertEquals(110, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOikeinJosOtetaanRahaa() {
        kortti.otaRahaa(8);
        assertEquals(2, kortti.saldo());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(20);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void palautetaanTrueJosRahatRiittavat() {
        assertEquals(true, kortti.otaRahaa(10));
    }
    
    @Test
    public void palautetaanFalseJosRahatEivatRiita() {
        assertEquals(false, kortti.otaRahaa(20));
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
