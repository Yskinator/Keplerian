/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

/**
 * Represents a celestial body with its own gravitational pull.
 * @author Ville-Matti Tanninen
 */
public class CelestialBody extends RigidBody {

    /**
     * Constructs a new CelestialBody. Note that all celestial bodies will be in orbit or stationary,
     * so only the orbit based constructor is needed.
     * @param mass Mass of the object.
     * @param orbit Orbit of the object.
     * @param parent The celestial body the object is orbiting around.
     */
    public CelestialBody(int mass, Orbit orbit, CelestialBody parent)
    {
        super(mass, orbit, parent);
    }
    
}
