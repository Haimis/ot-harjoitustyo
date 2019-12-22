
package kurssilaskuri.logics;

import kurssilaskuri.model.SumTable;
import kurssilaskuri.model.Etf;
import kurssilaskuri.model.DataBuilder;
import java.io.IOException;
import java.util.*;
import kurssilaskuri.model.DataPoint;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoursesServiceTest {
    CoursesService c;
    List<Etf> list;
    DataPoint dp;
    SumTable st;
    
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
        this.c = new CoursesService();
        DataBuilder d = new DataBuilder();
        this.list = d.parseData();
        this.dp = list.get(0).getDataPoints().get(0);
        this.st = new SumTable();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void returnsRightAverageOfDifferences() {
        ArrayList<Double> a = new ArrayList<>();
        a.add(0.0);
        a.add(1.23);
        a.add(0.17);
        a.add(-0.66);
        a.add(-0.0002);
        double res = c.averageOfDifferences(a);
        
        assertEquals(0.14796, res, 0.0000001);
    }
    
    @Test
    public void checkesTimeframeCorrectlyIfResIsTrue() {
        boolean res = c.checkIfCorrectTimeframe(this.dp, 6, 11);
        assertEquals(true, res);
    }
    
    @Test
    public void checkesTimeframeCorrectlyIfResIsFalse() {
        boolean res = c.checkIfCorrectTimeframe(this.dp, 6, 7);
        assertEquals(false, res);        
    }
    
    @Test
    public void updatesSumtable() {
        c.updateSumtable(st, dp, 1, 31, 1, 31);
        boolean res = (st.getFirstCounter() > 0 && st.getSecondCounter() > 0);
        assertEquals(true, res);  
    }
    
    @Test
    public void analysesMonthCorrectlyifCountersZero() {
        double res = c.analyseMonth(st);
        assertEquals(0.0, res, 0.000001);  
    }
    
    
    @Test
    public void analysesMonthCorrectlyIfCountersNotZero() {
        st.setFirstCounter(1);
        st.setSecondCounter(1);
        st.setFirstSum(4.3);
        st.setSecondSum(4.1);
        double res = c.analyseMonth(st);
        assertEquals((-0.046511628), res, 0.000001);  
    }
    
    @Test
    public void createsListWithContent() {
        boolean b = (list.size() > 0);
        assertEquals(true, b);
    }
    
    @Test
    public void infoBoxReturnsContent() {
        String s = c.infoBox();
        boolean res = (s.isEmpty());
        assertEquals(false, res);
    }
    
    @Test
    public void includeCorrectIndexes() {
        List<String> s = new ArrayList<>();
        s.add("EUNN.DE.csv");
        ArrayList<Integer> a = c.includeIndexes(s);
        int res = a.get(0);
        assertEquals(2, res);
    }
    
    @Test
    public void generateComparisonMethodReturnsText() {
        List<String> s = new ArrayList<>();
        s.add("EUNN.DE.csv");
        String str = c.generateComparison(s, 1, 31, 1, 18);
        boolean res = (str.isEmpty());
        assertEquals(false, res);
    }
    
    @Test
    public void getListWorks() {
        String res = c.getList().get(0).getName();
        assertEquals("DXET.DE.csv", res);
    }
    
    @Test
    public void firstComparisonIsCheaper() {
        List<String> l = new ArrayList<>();
        l.add(list.get(0).getName());
        String s = c.generateComparison(l, 1, 5, 1, 31);
        boolean res = s.contains("halvempia");
        assertEquals(true, res);
    }
    
    
    
    
    
    
    
    
    
    
    
}
