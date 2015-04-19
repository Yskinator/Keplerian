/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Contains handy little helper functions used by some of the tests.
 * @author Ville-Matti Tanninen
 */
public class Helper {
    
    
    /**
     * Converts degrees to radians.
     * @param deg Angle in degrees.
     * @return Angle in radians.
     */
    public static double toRadians(double deg)
    {
        return Math.PI/180.0*deg;
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
        bd = bd.round(new MathContext(places, RoundingMode.HALF_UP));
        return bd;
    }
    
    /**
    * Checks whether the two double values are 'close enough' to each other.
    * @param a double number one.
    * @param b double number two.
    * @return  Whether the numbers are approximately same or not.
    */
    public static boolean closeEnough(double a, double b)
    {
        if(a == Double.NaN || b == Double.NaN)
        {
            return false;
        }
        BigDecimal roundA = round(a, 1);
        BigDecimal roundB = round(b, 1);
        
        System.out.println("roundA: "+ roundA);
        System.out.println("roundB: " + roundB);
        
        return roundA.equals(roundB);
    }
    
    /**
     * Checks whether the orbits are more or less the same.
     * Calls closeEnough(double, double) for each orbital parameter.
     * @param a Orbit a.
     * @param b Orbit b.
     * @return Whether or not the orbits are more or less the same.
     */
    public static boolean closeEnough(Orbit a, Orbit b)
    {
        boolean aClose = closeEnough(a.getA(), b.getA());
        boolean eClose = closeEnough(a.getE(), b.getE());
        boolean iClose = closeEnough(a.getI(), b.getI());
        boolean mClose = closeEnough(a.getM(), b.getM());
        boolean omClose = closeEnough(a.getOm(), b.getOm());
        boolean wClose = closeEnough(a.getW(), b.getW());
        
        return aClose && eClose && iClose && mClose && omClose && wClose;
    }
    
}
