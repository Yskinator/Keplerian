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
     * @param vel
     * @return
     */
    public static Orbit findOrbit(double m, Vector3d r, Vector3d vel)
    {
        double e;
        double a;
        double p;
        double i;
        double om;
        double w;
        double v;
        
        double G = 6.673e-11;
        double mu = m*G;
        
        //Angular momentum.
        Vector3d h = Vector3d.crossProduct(r, vel);
        
        //Node vector
        Vector3d n = Vector3d.crossProduct(h, new Vector3d(0,0,1));
        n = n.unitVector();
        
        //Eccetricity vector
        Vector3d ev = Vector3d.mul((vel.magn()*vel.magn()-mu/r.magn()),r);
        
        //Eccentricity
        e = ev.magn();
        
        //Specific mechanical energy
        double E = vel.magn()*vel.magn()/2 - mu/r.magn();
        

        if(e!=1)
        {
            a = (-mu)/(2*E);
            p = a*(1-e*e);
        }
        else
        {
            a = Double.POSITIVE_INFINITY;
            p = (h.magn()*h.magn())/mu;
        }
        
        i = Math.acos((new Vector3d(0,0,h.z)).magn()/h.magn());
        om = Math.acos((new Vector3d(n.x,0,0)).magn()/n.magn());
        w = (Vector3d.dotProduct(n, ev))/(n.magn()*ev.magn());
        
        //true anomaly
        v = Math.acos(Vector3d.dotProduct(ev, r)/(ev.magn()*r.magn()));
        
        if(n.y < 0)
        {
            om = 2*Math.PI - om;
        }
        if((new Vector3d(0,0,ev.z).magn()) < 0)
        {
            w = 2*Math.PI - w;
        }
        if(Vector3d.dotProduct(r, vel) < 0)
        {
            v = 2*Math.PI - v;
        }
        
        return new Orbit(e,a,i,om,w,v);
        
    }
    
}
