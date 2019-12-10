package kurssilaskuri.logics;


import kurssilaskuri.model.Etf;
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
        Etf etf = new Etf("test");
        assertEquals("test", etf.getName());
    }
    
    @Test
    public void arrayListCreated() {
        Etf etf = new Etf("test");
        assertEquals(0, etf.getDataPoints().size());
    }
}
