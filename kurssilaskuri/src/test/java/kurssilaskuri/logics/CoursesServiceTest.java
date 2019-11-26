
package kurssilaskuri.logics;

import java.io.IOException;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoursesServiceTest {
    List<ETF> list;
    
    public CoursesServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        DataBuilder d = new DataBuilder();
        this.list = d.parseData();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createsListWithContent() {
        boolean b = (list.size() > 0);
        assertEquals(true, b);
    }
    
}
