
package keplerian.physics;

import static java.lang.Math.PI;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.cosh;
import static java.lang.Math.sin;
import static java.lang.Math.sinh;
import static java.lang.Math.sqrt;


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
        return new Orbit(o, o.getM()+o.getMu()*dt, t);
    }
    
    /**
     * Calculates the eccentric anomaly from true anomaly.
     * @param v True anomaly.
     * @param e Eccentricity.
     * @return Eccentric anomaly.
     */
    private static double findEccentricAnomalyV(double v, double e)
    {
        //Cosine formula seems to return -E instead of E. Using tangent formula instead.
        /*double E1 = e + Math.cos(v);
        E1 = E1/(1 + e * Math.cos(v));
        E1 = Math.acos(E1);*/
        double E2 = sqrt(1-e*e)*sin(v);
        E2 = E2/(e + cos(v));
        E2 = atan(E2);
        /*double E3 = sqrt(1-e*e)*sin(v);
        E3 = E3/(1+e*cos(v));
        E3 = asin(E3);*/
        return E2;
    }
    
    /**
     * calculates the eccentric anomaly from mean anomaly using Newton's method.
     * @param m Mean anomaly.
     * @param e Eccentricity.
     * @return  Eccentric anomaly.
     */
    private static double findEccentricAnomalyM(double m, double e)
    {
        /*double EPrev, f0,f1,f2,f3,d1,d2,d3,E;
        E = 0;
        do
        {
            EPrev = E;
            f0 = e*sinh(EPrev) - EPrev - m;
            f1 = e*cosh(EPrev) - 1;
            f2 = e*sinh(EPrev);
            f3 = e*cosh(EPrev);
            d1 = -f0/f1;
            d2 = -f0/(f1 + 0.5*d1*f2);
            d3 = -f0/(f1 + 0.5*d1*f2 + (1/6)*d2*f3);
            E = EPrev+d3;
        }while(Math.abs(E-EPrev)>1E-12);
        return E;*/
        double E, EPrev, f, df;
        E = m;
        do
        {
            EPrev = E;
            f = EPrev - e*sin(EPrev) - m;
            df = 1-cos(EPrev)*e;
            E = EPrev - (f/df);
        }while(Math.abs(E-EPrev)>1E-12);
        return E;
    }
    
    private static double findTrueAnomalyE(double E, double e)
    {
        double v = sqrt(1-e*e)*sin(E);
        v = v/(cos(E)-e);
        v = atan(v);
        return v;
    }
    
    private static double findRadius(Orbit o)
    {
        //True anomaly needed.
        double E = findEccentricAnomalyM(o.getM(), o.getE());
        double r = o.getA() * (1 - o.getE() * Math.cos(E));
        return r;
    }
    
    public static Vector3d findPosition(Orbit o, double t)
    {
        o = predictOrbit(o, t);
        double r = findRadius(o);
        double E = findEccentricAnomalyM(o.getM(), o.getE());
        double v = findTrueAnomalyE(E, o.getE());
        
        double om = o.getOm();
        double i = o.getI();
        double w = o.getW();
        
        double x, x1, x2, x3, y, y1, y2 ,y3, z, z1, z2 ,z3;
        //Canonical position
        x3 = r*cos(v);
        y3 = r*sin(v);
        z3 = 0;
        
        //Rotations
        
        x2 = x3*cos(w) - y3*sin(w);
        y2 = x3*sin(w) + y3*cos(w);
        z2 = z3;
        
        x1 = x2;
        y1 = y2*cos(i);
        z1 = y2*sin(i);
        
        x = x1*cos(om) - y1*sin(om);
        y = x1*sin(om) + y1*cos(om);
        z = z1;
        
        
        
        return new Vector3d(x,y,z);
    }
    
    /**
     * Calculates the orbital period of the given orbit.
     * @param o Orbit.
     * @return Orbital period.
     */
    public static double findOrbitalPeriod(Orbit o)
    {
        double a = o.getA();
        double mu = o.getMu();
        return 2*PI*sqrt(a*a*a/mu);
    }
    
    /**
     * Calculates the mean anomaly from true anomaly.
     * @param v True anomaly.
     * @param e Eccentricity.
     * @return Mean anomaly.
     */
    private static double findMeanAnomaly(double v, double e)
    {
        double E = findEccentricAnomalyV(v, e);
        double M = E - (e * Math.sin(E));
        if(M < 0)
        {
            M = M + 2*Math.PI;
        }
        return M;
    }
    
}
