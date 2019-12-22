
package kurssilaskuri.model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SumtableTest {
    SumTable st;
    
    public SumtableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.st = new SumTable();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void initializeWorks() {
        st.setFirstCounter(7);
        st.setFirstSum(13);
        st.setSecondCounter(2);
        st.setSecondSum(1);
        st.initializeTable();
        boolean res = ((st.getFirstCounter() + st.getFirstSum() + st.getSecondCounter() + st.getSecondSum()) == 0);
        assertEquals(true, res);
    }
}
