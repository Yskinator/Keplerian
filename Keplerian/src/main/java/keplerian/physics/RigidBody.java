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
public class RigidBody {
    
    private float mass;
    private Orbit orbit;
    private Vector3d relVel;
    private Vector3d relPos;
    private CelestialBody parent;
    
    private static final Orbit NO_ORBIT = null;
    
    /**
     * Creates a new RigidBody based on position and velocity. Calculates Orbit.
     * @param mass
     * @param pos
     * @param vel
     * @param parent 
     */
    public RigidBody(int mass, Vector3d pos, Vector3d vel, CelestialBody parent)
    {
        this.mass = mass;
        this.relPos = pos;
        this.relVel = vel;
        
        this.toRails();
    }
    
    /**
     * Creates a new RigidBody based on an existing orbit.
     * @param mass
     * @param orbit 
     */
    public RigidBody(int mass, Orbit orbit)
    {
        this.mass = mass;
        this.orbit = orbit;
    }
    
    /**
     * Calculates the orbit.
     */
    public void toRails()
    {
        float m = parent.getMass();
        this.orbit = TwoBodySolver.findOrbit(m, relPos, relVel);
    }
    
    public void offRails()
    {
        relVel = orbit.findOrbitalVelocity();
        relPos = orbit.findPosition();
        
        this.orbit = NO_ORBIT;
    }

    /**
     * @return the mass
     */
    public float getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * @return the orbit
     */
    public Orbit getOrbit() {
        return orbit;
    }

    /**
     * @return the relVel
     */
    public Vector3d getRelVel() {
        return relVel;
    }

    /**
     * @return the relPos
     */
    public Vector3d getRelPos() {
        return relPos;
    }

    /**
     * @return the parent
     */
    public CelestialBody getParent() {
        return parent;
    }
}
