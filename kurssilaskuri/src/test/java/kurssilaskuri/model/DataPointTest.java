package kurssilaskuri.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataPointTest {
    DataPoint dp;
    
    public DataPointTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dp = new DataPoint(2019, 10, 12, 30.11);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorWorks() {
        assertEquals( dp, this.dp);
    }
    
    @Test
    public void returnsRightMonth() {
        assertEquals(10, dp.getMonth());
    }
}
