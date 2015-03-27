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
public class CelestialBody extends RigidBody {

    public CelestialBody(int mass, Vector2d pos, Vector2d vel, CelestialBody parent) {
        super(mass, pos, vel, parent);
    }
    
    public CelestialBody(int mass, Orbit orbit)
    {
        super(mass, orbit);
    }
    
}
