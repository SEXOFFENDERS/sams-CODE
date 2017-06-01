import junit.framework.TestCase;

public class TriangleClassifierTest extends TestCase {

    TriangleClassifier tc;
    
    protected void setUp() throws Exception {
        super.setUp();
    }

    public TriangleClassifierTest() {
        tc = new TriangleClassifier();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
        
    public void testRightScalene() {
        assertEquals("right scalene", tc.getClassification(30, 60, 90));
        assertEquals("right scalene", tc.getClassification(30, 90, 60));
        assertEquals("right scalene", tc.getClassification(60, 30, 90));
        assertEquals("right scalene", tc.getClassification(60, 90, 30));
        assertEquals("right scalene", tc.getClassification(90, 30, 60));
        assertEquals("right scalene", tc.getClassification(90, 60, 30));
    }
    
    public void testRightIsosceles() {
        assertEquals("right isosceles", tc.getClassification(45, 45, 90));
        assertEquals("right isosceles", tc.getClassification(45, 90, 45));
        assertEquals("right isosceles", tc.getClassification(90, 45, 45));
    }

    public void testObtuseIsosceles() {
        assertEquals("obtuse isosceles", tc.getClassification(100, 40, 40));
        assertEquals("obtuse isosceles", tc.getClassification(40, 100, 40));
        assertEquals("obtuse isosceles", tc.getClassification(40, 40, 100));
    }
    
    public void testObtuseScalene() {
        assertEquals("obtuse scalene", tc.getClassification(100, 60, 20));
        assertEquals("obtuse scalene", tc.getClassification(100, 20, 60));
        assertEquals("obtuse scalene", tc.getClassification(60, 20, 100));
        assertEquals("obtuse scalene", tc.getClassification(60, 100, 20));
        assertEquals("obtuse scalene", tc.getClassification(20, 100, 60));
        assertEquals("obtuse scalene", tc.getClassification(20, 60, 100));
    }

    public void testEquilateral() {
        assertEquals("equilateral", tc.getClassification(60, 60, 60));
    }

    public void testAcuteIsosceles() {
        assertEquals("acute isosceles", tc.getClassification(80, 50, 50));
        assertEquals("acute isosceles", tc.getClassification(50, 50, 80));
        assertEquals("acute isosceles", tc.getClassification(50, 80, 50));
    }
    
    public void testAcuteScalene() {
        assertEquals("acute scalene", tc.getClassification(50, 60, 70));
        assertEquals("acute scalene", tc.getClassification(50, 70, 60));
        assertEquals("acute scalene", tc.getClassification(60, 70, 50));
        assertEquals("acute scalene", tc.getClassification(60, 50, 70));
        assertEquals("acute scalene", tc.getClassification(70, 50, 60));
        assertEquals("acute scalene", tc.getClassification(70, 60, 50));
    }

    public void testSumGreaterThan180() {
        assertEquals("INVALID", tc.getClassification(30, 90, 90));
        assertEquals("INVALID", tc.getClassification(90, 30, 90));
        assertEquals("INVALID", tc.getClassification(90, 90, 30));
    }

    public void testZeroAngle() {
        assertEquals("INVALID", tc.getClassification(120, 60, 0));
        assertEquals("INVALID", tc.getClassification(60, 120, 0));
        assertEquals("INVALID", tc.getClassification(0, 60, 10));        
    }

}
