/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

import static java.lang.Double.NaN;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author villtann
 */
public class Vector3dTest {
    
    public Vector3dTest() {
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

    // TODO sum test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /**
     * Tests whether the constructor constructs a vector. Because reasons.
     */
    @Test
    public void constructorTest()
    {
        Vector3d v = new Vector3d(500,-500,0);
        assertEquals("(500.0, -500.0, 0.0)", v.toString());
        assertTrue(v.x == 500.0);
        assertTrue(v.y == -500.0);
        assertTrue(v.z == 0.0);
        
    }
    
    /**
     * Tests whether vectors with a single undefined component are detected correctly by isUndefined().
     */
    @Test
    public void oneNaNDetected()
    {
        Vector3d v = new Vector3d(0, 0, Double.NaN);
        assertEquals(true, v.isUndefined());
        v = new Vector3d(0,Double.NaN,0);
        assertEquals(true, v.isUndefined());
        v = new Vector3d(Double.NaN, 0, 0);
        assertEquals(true, v.isUndefined());
    }
    
    /**
     * Tests whether vectors where all components are undefined are detected correctly by isUndefined().
     */
    @Test
    public void allNaNDetected()
    {
        Vector3d v = new Vector3d(NaN, NaN, NaN);
        assertEquals(true, v.isUndefined());
    }
    
    /**
     * Tests whether vectors where all components are defined are detected as defined.
     */
    @Test
    public void noNanDetected()
    {
        Vector3d v = new Vector3d(0,0,0);
        assertEquals(false, v.isUndefined());
    }
    
    /**
     * Tests whether vector multiplication by scalar works.
     */
    @Test
    public void mulWorks()
    {
        Vector3d v = new Vector3d(0,1,5232);
        v = Vector3d.mul(v, 10);
        assertEquals(new Vector3d(0,10,52320), v);
        v = Vector3d.mul(0.1, v);
        assertEquals(new Vector3d(0,1,5232), v);
    }
    
    /**
     * Tests whether vector division by scalar works.
     */
    @Test
    public void divWorks()
    {
        Vector3d v = new Vector3d(0,-2, 10);
        v = Vector3d.div(v, 2);
        assertEquals(new Vector3d(0,-1,5), v);
    }
    
    /**
     * Tests whether the magnitude of a vector is calculated correctly.
     */
    @Test
    public void magnWorks()
    {
        Vector3d v = new Vector3d(0,0,10);
        double magn = v.magn();
        assertTrue(10.0 == magn);
    }
    
    /**
     * Tests whether the matching unit vector is calculated correctly.
     */
    @Test
    public void unitVectorCalculatedCorrectly()
    {
        Vector3d v = new Vector3d(0,0,10);
        v = v.unitVector();
        assertEquals(new Vector3d(0,0,1), v);
    }
    
    /**
     * Tests whether or not vector addition works correctly.
     */
    @Test
    public void sumWorks()
    {
        Vector3d v = new Vector3d(0,0,1);
        Vector3d u = new Vector3d(0,1,0);
        v = Vector3d.sum(u, v);
        assertEquals(new Vector3d(0,1,1), v);
    }
    
    /**
     * Tests whether or not vector subtraction works correctly.
     */
    @Test
    public void subWorks()
    {
        Vector3d v = new Vector3d(0,0,1);
        Vector3d u = new Vector3d(0,1,0);
        v = Vector3d.sub(u, v);
        assertEquals(new Vector3d(0,1,-1), v);
    }
    
    /**
     * Tests whether or not cross product is calculated correctly.
     */
    @Test
    public void crossProductWorks()
    {
        Vector3d v = new Vector3d(2,4,8);
        Vector3d u = new Vector3d(-2,-8, 0);
        v = Vector3d.crossProduct(v, u);
        assertEquals(new Vector3d(64, -16, -8), v);
    }
    
    @Test
    public void dotProduct()
    {
        Vector3d v = new Vector3d(2,4,8);
        Vector3d u = new Vector3d(-2,-8, 0);
        double dot = Vector3d.dotProduct(u, v);
        assertTrue(-36.0 == dot);
    }
}
