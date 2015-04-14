
package keplerian.physics;


/**
 * Responsible for solving the two body problem to find the orbital elements.
 * @author Ville-Matti Tanninen
 */
public class TwoBodySolver {
    
    final static float G = 6.673e-11f;
    
    /**
     * Calculates the orbit.
     * 
     * Note that orbits where eccentricity or inclination are close to zero will cause issues, or more
     * specifically, singularities. For those cases something like mean longitude should be used instead.
     * @param m Mass of the parent object, used to calculate the gravitational pull.
     * @param r Position of the object.
     * @param vel Velocity of the object
     * @param t Current time.
     * @return Orbit of the object.
     */
    public static Orbit findOrbit(double m, Vector3d r, Vector3d vel, double t)
    {
        //The orbital elements
        double e;
        double a;
        double i;
        double om;
        double w;
        double v;
        
        double mu = m*G;
        
        Vector3d h = findAngularMomentum(r, vel);
        Vector3d n = findNodeVector(h);
        
        Vector3d ev = findEccentricityVector(vel,   mu, r);
        double E = findSpecificMechanicalEnergy(vel, mu, r);
        
        e = findEccentricity(ev);
        a = findSemiMajorAxis(e, mu, E);
        i = findInclination(h);
        om = findLongitudeOfAscendingNode(n);
        w = findArgumentOfPeriapsis(n, ev);
        v = findTrueAnomaly(ev, r, vel);
        m = findMeanAnomaly(v, e);
        
        return new Orbit(e,a,i,om,w,m, mu,t);
        
    }

    /**
     * Calculates the specific mechanical energy.
     * 
     * Used when calculating semi-major axis.
     * @param vel Velocity.
     * @param mu m*G.
     * @param r Position.
     * @return Specific mechanical energy.
     */
    private static double findSpecificMechanicalEnergy(Vector3d vel, double mu, Vector3d r) {
        double E = vel.magn()*vel.magn()/2 - mu/r.magn();
        return E;
    }

    /**
     * Calculates argument of the periapsis, one of the orbital elements.
     * @param n Node vector.
     * @param ev Eccentricity vector.
     * @return Argument of the periapsis.
     */
    private static double findArgumentOfPeriapsis(Vector3d n, Vector3d ev) {
        double w;
        if(n.isUndefined())
        {
            w = Math.atan2(ev.y, ev.x);
        }
        else
        {
            w = Math.acos(Vector3d.dotProduct(n, ev)/(n.magn()*ev.magn()));
        }
        
        if((new Vector3d(0,0,ev.z).magn()) > 0)
        {
            w = 2*Math.PI - w;
        }
        
        return w;
    }

    /**
     * Calculates true anomaly, one of the orbital elements.
     * @param ev Eccentricity vector.
     * @param r Position.
     * @param vel Velocity.
     * @return True anomaly.
     */
    private static double findTrueAnomaly(Vector3d ev, Vector3d r, Vector3d vel) {
        double v;
        v = Math.acos(Vector3d.dotProduct(ev, r)/(ev.magn()*r.magn()));
        
        if(Vector3d.dotProduct(r, vel) < 0)
        {
            v = 2*Math.PI - v;
        }
        
        return v;
    }

    /**
     * Calculates eccentricity, one of the orbital elements.
     * @param ev Eccentricity vector.
     * @return Eccentricity.
     */
    private static double findEccentricity(Vector3d ev) {
        double e;
        //Eccentricity
        e = ev.magn();
        return e;
    }

    /**
     * Calculates the angular momentum.
     * 
     * Used when calculating inclination and node vector..
     * @param r Position.
     * @param vel Velocity.
     * @return Angular momentum.
     */
    private static Vector3d findAngularMomentum(Vector3d r, Vector3d vel) {
        Vector3d h = Vector3d.crossProduct(r, vel);
        return h;
    }

    /**
     * Calculates the node vector.
     * 
     * Used when calculating argument of the periapsis and argument of the longitude.
     * @param h Angular momentum.
     * @return Node vector.
     */
    private static Vector3d findNodeVector(Vector3d h) {
        Vector3d n = Vector3d.crossProduct(new Vector3d(0,0,1), h);
        n = n.unitVector();
        return n;
    }

    /**
     * Calculates the semi-latus rectum.
     * 
     * Can be used when calculating position and velocity from the orbital parameters.
     * @param e Eccentricity.
     * @param a Semi-major axis.
     * @param h Angular momentum.
     * @param mu m*G.
     * @return Semi-latus rectum.
     */
    private static double findSemiLatusRectum(double e, double a, Vector3d h, double mu) {
        double p;
        if(e!=1)
        {
            p = a*(1-e*e);
        }
        else
        {
            p = (h.magn()*h.magn())/mu;
        }
        return p;
    }

    /**
     * Calculates the semi-major axis, one of the orbital elements.
     * @param e Eccentricity.
     * @param mu m*G.
     * @param E Specific mechanical energy.
     * @return Semi-major axis.
     */
    private static double findSemiMajorAxis(double e, double mu, double E) {
        double a;
        if(e!=1)
        {
            a = (-mu)/(2*E);
        }
        else
        {
            a = Double.POSITIVE_INFINITY;
        }
        return a;
    }

    /**
     * Calculates the longitude of the ascending node, one of the orbital elements.
     * @param n Node vector.
     * @return Longitude of the ascending node.
     */
    private static double findLongitudeOfAscendingNode(Vector3d n) {
        double om;
        
        if(n.isUndefined())
        {
            om = 0;
        }
        else
        {
            om = Math.acos(n.x/n.magn());
        }
        
        if(n.y < 0)
        {
            om = 2*Math.PI - om;
        }
        
        return om;
    }

    /**
     * Calculates the inclination, one of the orbital elements.
     * @param h Angular momentum.
     * @return Inclination.
     */
    private static double findInclination(Vector3d h) {
        double i;
        i = Math.acos(h.z/h.magn());
        return i;
    }

    /**
     * Calculates eccentricity, one of the orbital elements.
     * @param vel Velocity.
     * @param mu m*G.
     * @param r Position.
     * @return Eccentricity.
     */
    private static Vector3d findEccentricityVector(Vector3d vel, double mu, Vector3d r) {
        //Eccetricity vector
        Vector3d ev = Vector3d.mul((vel.magn()*vel.magn()-mu/r.magn()),r);
        ev = Vector3d.sub(ev, Vector3d.mul(Vector3d.dotProduct(r, vel), vel));
        ev = Vector3d.div(ev, mu);
        return ev;
    }
    
    /**
     * Predicts the new orbital parameters after given change of time.
     * @param o Current orbit.
     * @param t Desired time.
     * @return New orbit.
     */
    private static Orbit predictOrbit(Orbit o, double t)
    {
        double dt = t-o.getT();
        return new Orbit(o, o.getMu()*dt, t);
    }
    
    /**
     * Calculates the eccentric anomaly from true anomaly.
     * @param v True anomaly.
     * @param e Eccentricity.
     * @return Eccentric anomaly.
     */
    private static double findEccentricAnomaly(double v, double e)
    {
        double E = e + Math.cos(v);
        E = E/(1 + e * Math.cos(v));
        E = Math.acos(E);
        return E;
    }
    
    /**
     * Calculates the mean anomaly from true anomaly.
     * @param v True anomaly.
     * @param e Eccentricity.
     * @return Mean anomaly.
     */
    private static double findMeanAnomaly(double v, double e)
    {
        double E = findEccentricAnomaly(v, e);
        double M = E - (Math.E * Math.sin(E));
        if(M < 0)
        {
            M = M + 2*Math.PI;
        }
        return M;
    }
    
}
