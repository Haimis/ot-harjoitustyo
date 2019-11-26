package kurssilaskuri.logics;


import kurssilaskuri.logics.ETF;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ETFTest {
    
    public ETFTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void name() {
        ETF etf = new ETF("test");
        assertEquals("test", etf.getName());
    }
    
    @Test
    public void arrayListCreated() {
        ETF etf = new ETF("test");
        assertEquals(0, etf.getDataPoints().size());
    }
}
