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
    private float e;
    
    /**
     * Semi-major axis.
     */
    private float a;
    
    /**
     * Inclination.
     */ 
    private float i;
    
    /**
     * Longitude of the ascending node, usually marked as capital omega.
     */
    private float om;
    
    /**
     * Argument of the periapsis, usually marked as lower care omega.
     */
    private float w;
    
    /**
     * Mean anomaly.
     */
    private float M0;
    
    /**
     * Constructs a new orbit.
     * @param e
     * @param a
     * @param i
     * @param om
     * @param w
     * @param M0 
     */
    public Orbit(float e, float a, float i, float om, float w, float M0)
    {
        this.e = e;
        this.a = a;
        this.i = i;
        this.om = om;
        this.w = w;
        this.M0 = M0;
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
