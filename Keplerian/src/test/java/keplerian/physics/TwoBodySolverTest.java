/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

import java.util.ArrayList;
import static keplerian.physics.Helper.closeEnough;
import static keplerian.physics.Helper.toRadians;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Used to test the two body solver.
 * @author Ville-Matti Tanninen
 */
public class TwoBodySolverTest {
    ArrayList<Orbit> correctOrbits = new ArrayList<>();
    ArrayList<Orbit> resultOrbits = new ArrayList<>();
    ArrayList<Double> masses = new ArrayList<>();
    ArrayList<Vector3d> positions = new ArrayList<>();
    ArrayList<Vector3d> velocities = new ArrayList<>();
    
    /**
     * Orbits are calculated ahead of time as each parameter is tested individually.
     * The calculator used to find the correct test values can be found here: http://orbitsimulator.com/formulas/OrbitalElements.html.
     */
    public TwoBodySolverTest() {
        double m;
        Vector3d r;
        Vector3d vel;
        Orbit oC;
        Orbit oR;
        
        m = 1.98891691172467E+30;
        masses.add(m);
        r = new Vector3d(617244712357.9814, -431694791368.0269, -12036457087.000256);
        positions.add(r);
        vel = new Vector3d(7320.00000000049, 11328.999999999676, -211.00000000002197);
        velocities.add(vel);
        oC = new Orbit(0.04842547228152425, 778194564622.5737, toRadians(1.3051851018817964), toRadians(100.4943151229807), toRadians(275.3654041542324), toRadians(313.38300121665685), 0, 0);
        correctOrbits.add(oC);
        oR = TwoBodySolver.findOrbit(m, r, vel, 0);
        resultOrbits.add(oR);
        
        m = 1.98891691172467E+30;
        masses.add(m);
        r = new Vector3d(617244712357.9814, -431694791368.0269, 0.0);
        positions.add(r);
        vel = new Vector3d(7320.00000000049, 11328.999999999676, 0.0);
        velocities.add(vel);
        oC = new Orbit(0.048368987403347746, 777786552616.2518, toRadians(0), toRadians(0), toRadians(16.411667325491873), toRadians(312.8531705822729), 0, 0);
        correctOrbits.add(oC);
        oR = TwoBodySolver.findOrbit(m, r, vel, 0);
        resultOrbits.add(oR);
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

    

    

    
    /**
     * Tests whether or not eccentricity is calculated correctly.
     * @param i Index used to find the correct orbits to test.
     */
    private void testEccentricity(int i)
    {
       double eResult, eCorrect;
       eResult = resultOrbits.get(i).getE();
       eCorrect = correctOrbits.get(i).getE();
       
       String error = "Eccentricity calculated incorrectly. Received " + eResult + ", expected " + eCorrect;
       
       assertTrue(error, closeEnough(eResult,eCorrect));
    }
    
    /**
     * Tests whether or not semi-major axis is calculated correctly.
     * @param i Index used to find the correct orbits to test.
     */
    private void testSemiMajorAxis(int i)
    {
       double aResult, aCorrect;
       aResult = resultOrbits.get(i).getA();
       aCorrect = correctOrbits.get(i).getA();
       
       String error = "Semi-major axis calculated incorrectly. Received " + aResult + ", expected " + aCorrect;
       
       assertTrue(error, closeEnough(aResult,aCorrect));
    }
    
    /**
     * Tests whether or not inclination is calculated correctly.
     * @param i Index used to find the correct orbits to test.
     */
    private void testInclination(int i)
    {
       double iResult, iCorrect;
       iResult = resultOrbits.get(i).getI();
       iCorrect = correctOrbits.get(i).getI();
       
       String error = "Inclination calculated incorrectly. Received " + iResult + ", expected " + iCorrect;
       
       assertTrue(error, closeEnough(iResult,iCorrect));
    }
    
    /**
     * Tests whether or not longitude of the ascending node is calculated correctly.
     * @param i Index used to find the correct orbit to test.
     */
    private void testLongitudeOfTheAscendingNode(int i)
    {
       double omResult, omCorrect;
       omResult = resultOrbits.get(i).getOm();
       omCorrect = correctOrbits.get(i).getOm();
       
       String error = "Longitude of the ascending node calculated incorrectly. Received " + omResult + ", expected " + omCorrect;
       
       assertTrue(error, closeEnough(omResult,omCorrect));
    }
    
    /**
     * Tests whether or not argument of the periapsis is calculated correctly.
     * @param i Index used to find the correct orbit to test.
     */
    private void testArgumentOfPeriapsis(int i)
    {
       double wResult, wCorrect;
       wResult = resultOrbits.get(i).getW();
       wCorrect = correctOrbits.get(i).getW();
       
       String error = "Argument of the periapsis calculated incorrectly. Received " + wResult + ", expected " + wCorrect;
       
       assertTrue(error, closeEnough(wResult,wCorrect));
    }
    
    /**
     * Tests whether or not mean anomaly is calculated correctly.
     * @param i Index used to find the correct orbit to test.
     */
    private void testMeanAnomaly(int i)
    {
       double mResult, mCorrect;
       mResult = resultOrbits.get(i).getM();
       mCorrect = correctOrbits.get(i).getM();
       
       String error = "Mean anomaly calculated incorrectly. Received " + mResult + ", expected " + mCorrect;
       
       assertTrue(error, closeEnough(mResult,mCorrect));
    }
    
    private void testPosition(int i)
    {
        Vector3d rResult, rCorrect;
        rResult = TwoBodySolver.findPosition(resultOrbits.get(i),0);
        rCorrect = positions.get(i);
        String error = "Position calculated incorrectly. Received " + rResult + ", expected " + rCorrect;
        assertTrue(error, closeEnough(rResult, rCorrect));
    }
    
    /**
     * Test whether or not non-equatorial orbit's eccentricity is calculated correctly.
     */
    @Test
    public void nonEquatorialEccentricity()
    {
        testEccentricity(0);
    }
    
    /**
     * Test whether or not non-equatorial orbit's semi-major axis is calculated correctly.
     */
    @Test
    public void nonEquatorialSemiMajorAxis()
    {
        testSemiMajorAxis(0);
    }
    
    /**
     * Test whether or not non-equatorial orbit's inclination is calculated correctly.
     */
    @Test
    public void nonEquatorialInclination()
    {
        testInclination(0);
    }
    
    /**
     * Test whether or not non-equatorial orbit's longitude of the ascending node is calculated correctly.
     */
    @Test
    public void nonEquatorialLongitudeOfTheAscendingNode()
    {
        testLongitudeOfTheAscendingNode(0);
    }
    
    /**
     * Test whether or not non-equatorial orbit's argument of periapsis is calculated correctly.
     */
    @Test
    public void nonEquatorialArgumentOfPeriapsis()
    {
        testArgumentOfPeriapsis(0);
    }
    
    /**
     * Test whether or not non-equatorial orbit's mean anomaly is calculated correctly.
     */
    @Test
    public void nonEquatorialMeanAnomaly()
    {
        testMeanAnomaly(0);
    }
    
    @Test
    public void nonEquatorialPosition()
    {
        testPosition(0);
    }
    
    /**
     * Test whether or not equatorial orbit's eccentricity is calculated correctly.
     */
    @Test
    public void equatorialEccentricity()
    {
        testEccentricity(1);
    }
    
    /**
     * Test whether or not equatorial orbit's semi-major axis is calculated correctly.
     */
    @Test
    public void equatorialSemiMajorAxis()
    {
        testSemiMajorAxis(1);
    }
    
    /**
     * Test whether or not equatorial orbit's inclination is calculated correctly.
     */
    @Test
    public void equatorialInclination()
    {
        testInclination(1);
    }
    
    /**
     * Test whether or not equatorial orbit's longitude of the ascending node is calculated correctly.
     */
    @Test
    public void equatorialLongitudeOfTheAscendingNode()
    {
        testLongitudeOfTheAscendingNode(1);
    }
    
    /**
     * Test whether or not equatorial orbit's argument of periapsis is calculated correctly.
     */
    @Test
    public void equatorialArgumentOfPeriapsis()
    {
        testArgumentOfPeriapsis(1);
    }
    
    /**
     * Test whether or not equatorial orbit's mean anomaly is calculated correctly.
     */
    @Test
    public void equatorialMeanAnomaly()
    {
        testMeanAnomaly(1);
    }
    
    @Test
    public void equatorialPosition()
    {
        testPosition(1);
    }
    
}
