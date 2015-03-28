/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

/**
 * Represents a celestial body with its own gravitational pull. Unfinished.
 * @author Ville-Matti Tanninen
 */
public class CelestialBody extends RigidBody {

    public CelestialBody(int mass, Vector3d pos, Vector3d vel, CelestialBody parent) {
        super(mass, pos, vel, parent);
    }
    
    public CelestialBody(int mass, Orbit orbit, CelestialBody parent)
    {
        super(mass, orbit, parent);
    }
    
}
