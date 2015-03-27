/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

import org.jsfml.system.Vector2f;

/**
 *
 * @author villtann
 */
public class TwoBodySolver {
    
    /**
     * Calculates the orbit.
     * @param m Mass of the parent object, used to calculate the gravitational pull.
     * @param r
     * @param v
     * @return
     */
    public static Orbit findOrbit(double m, Vector3d r, Vector3d v)
    {
        double e;
        
        double G = 6.673e-11;
        double mu = m*G;
        
        //Angular momentum.
        Vector3d h = Vector3d.crossProduct(r, v);
        
        //Node vector
        Vector3d n = Vector3d.crossProduct(h, new Vector3d(0,0,1));
        
        //Eccetricity vector
        Vector3d ev = Vector3d.mul((v.magn()*v.magn()-mu/r.magn()),r);
        
        //Eccentricity
        e = ev.magn();
        
        return null;
        
    }
    
}
