/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

/**
 *
 * @author villtann
 */
class Orbit {
    
    /**
     * Eccentricity.
     */
    private double e;
    
    /**
     * Semi-major axis.
     */
    private double a;
    
    /**
     * Inclination.
     */ 
    private double i;
    
    /**
     * Longitude of the ascending node, usually marked as capital omega.
     */
    private double om;
    
    /**
     * Argument of the periapsis, usually marked as lower care omega.
     */
    private double w;
    
    /**
     * True anomaly.
     */
    private double v;
    
    /**
     * Constructs a new orbit.
     * @param e
     * @param a
     * @param i
     * @param om
     * @param w
     * @param v 
     */
    public Orbit(double e, double a, double i, double om, double w, double v)
    {
        this.e = e;
        this.a = a;
        this.i = i;
        this.om = om;
        this.w = w;
        this.v = v;
    }
    
    
    public Vector3d findOrbitalVelocity()
    {
        throw new UnsupportedOperationException();
    }
    
    public Vector3d findPosition()
    {
        throw new UnsupportedOperationException();
    }
}
