/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
        oC = new Orbit(0.04842547228152425, 778194564622.5737, 1.3051851018817964, 100.4943151229807, 275.3654041542324, 309.17915580554745);
        correctOrbits.add(oC);
        oR = TwoBodySolver.findOrbit(m, r, vel);
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
     * Takes in a double and desired precision, spits out a big decimal of desired precision.
     * 
     * Result is BigDecimal because the numbers will be compared later. Far easier than floating points.
     * @param value The original value to be rounded.
     * @param places The number of decimal places desired.
     * @return A big decimal representing the value rounded to the desired number of decimal places.
     */
    public static BigDecimal round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }
    
    /**
     * Compares two doubles to five decimal places.
     * @param a double number one.
     * @param b double number two.
     * @return  Whether the numbers are approximately same or not.
     */
    private boolean closeEnough(double a, double b)
    {
        if(a == Double.NaN || b == Double.NaN)
        {
            return false;
        }
        BigDecimal roundA = round(a, 5);
        BigDecimal roundB = round(b, 5);
        
        return roundA.equals(roundB);
    }
    
    
    
}
