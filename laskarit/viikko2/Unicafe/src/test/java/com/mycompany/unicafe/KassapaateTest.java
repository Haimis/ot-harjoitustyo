package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    Maksukortti kortti2;
    Maksukortti kortti3;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        kortti2 = new Maksukortti(50);
        kortti3 = new Maksukortti(300);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kassapaatteenRahamaaraOnAlussaOikea() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjenMaukkauidenLounaidenMaaraOnAlussaOikea() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraOnAlussaOikea() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void riittavallaKateisellaEdullisenVaihtorahaOikein() {
        int vaihtoraha = kassa.syoEdullisesti(500);
        assertEquals(260, vaihtoraha);
    }
    
    @Test
    public void riittavallaKateisellaMaukkaanVaihtorahaOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertEquals(100, vaihtoraha);        
    }
    
    @Test
    public void riittavallaKateisellaEdullistenLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void riittavallaKateisellaMaukkaidenLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateinenEiRiitaEdulliseenKassanRahamaaraEiMuutu() {
        kassa.syoEdullisesti(50);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateinenEiRiitaEdulliseenKaikkiRahatPalautetaanVaihtorahana() {
        int vaihtoraha = kassa.syoMaukkaasti(50);
        assertEquals(50, vaihtoraha);
    }
    
    @Test
    public void kateinenEiRiitaEdulliseenMyytyjenLounaidenMaaraEiKasva() {
        kassa.syoEdullisesti(50);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void kateinenEiRiitaMaukkaaseenKassanRahamaaraEiMuutu() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateinenEiRiitaMaukkaaseenKaikkiRahatPalautetaanVaihtorahana() {
        int vaihtoraha = kassa.syoMaukkaasti(300);
        assertEquals(300, vaihtoraha);
    }
    
    @Test
    public void kateinenEiRiitaMaukkaaseenMyytyjenLounaidenMaaraEiKasva() {
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void riittavallaKortillaEdullisenVeloitusOikein() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void riittavallaKortillaMaukkaanVeloitusOikein() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void riittavallaKortillaEdullistenLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void riittavallaKortillaMaukkaidenLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinKateEiRiitaEdulliseenKortinRahamaaraEiMuutu() {
        kassa.syoEdullisesti(kortti2);
        assertEquals(50, kortti2.saldo());
    }
    
    @Test
    public void kortinKateEiRiitaEdulliseenMyytyjenLounaidenMaaraEiMuutu() {
        kassa.syoEdullisesti(kortti2);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortinKateEiRiitaMaukaaseenKortinRahamaaraEiMuutu() {
        kassa.syoMaukkaasti(kortti3);
        assertEquals(300, kortti3.saldo());
        
    }

    @Test
    public void kortinKateEiRiitaMaukkaaseenMyytyjenLounaidenMaaraEiMuutu() {
        kassa.syoMaukkaasti(kortti3);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiMuttuKorttimaksussa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadattaessaKortinSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti2, 50);
        assertEquals(100, kortti2.saldo());
    }
    
    @Test
    public void kortilleLadattaessaKassanSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortille() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
    }



}
