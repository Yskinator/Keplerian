

package keplerian.physics;

/**
 * Represents an orbit around a celestial body.
 * @author Ville-Matti Tanninen
 */
public class Orbit {
    
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
     * Mean anomaly.
     */
    private double m;
    
    /**
     * Mu, calculated from mass*G. Stored for convenience. 
     */
    private double mu;
    
    /**
     * Time at which the orbit was calculated. Used when finding delta times.
     */
    private double t;
    
    /**
     * Constructs a new orbit.
     * @param e Eccentricity
     * @param a Semi-major axis.
     * @param i Inclination
     * @param om Longitude of the ascending node
     * @param w Argument of the periapsis.
     * @param m Mean anomaly.
     * @param mu mass*G. Used when predicting orbits, stored for convenience.
     * @param t Epoch time.
     */
    public Orbit(double e, double a, double i, double om, double w, double m, double mu, double t)
    {
        this.e = e;
        this.a = a;
        this.i = i;
        this.om = om;
        this.w = w;
        this.m = m;
        this.mu = mu;
        this.t = t;
    }
    
    /**
     * Constructs a new orbit, identical to the previous one except for the new mean anomaly.
     * Used to easily make new orbits when predicting orbits - only mean anomaly changes.
     * @param o Original orbit.
     * @param m Mean anomaly.
     */
    public Orbit(Orbit o, double m)
    {
        this.e = o.getE();
        this.a = o.getA();
        this.i = o.getI();
        this.om = o.getOm();
        this.w = o.getW();
        this.m = m;
        this.mu = o.getMu();
        this.t = o.getT();
    }
    
    /**
     * Calculates the velocity of the object at the given time.
     * @param t Time.
     * @return Velocity of the object.
     */
    public Vector3d findOrbitalVelocity(float t)
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Calculates the position of the object at the given time.
     * @param t Time.
     * @return Position of the object.
     */
    public Vector3d findPosition(float t)
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString()
    {
        String s = "e: " + getE() + " a: " + getA() + " i: " + getI() + " om: " + getOm() + " w: " + getW() + " m: " + getM();
        return s;
    }

    /**
     * @return the e
     */
    public double getE() {
        return e;
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @return the i
     */
    public double getI() {
        return i;
    }

    /**
     * @return the om
     */
    public double getOm() {
        return om;
    }

    /**
     * @return the w
     */
    public double getW() {
        return w;
    }

    /**
     * @return the m
     */
    public double getM() {
        return m;
    }

    /**
     * @return the mu
     */
    public double getMu() {
        return mu;
    }

    /**
     * @return the t
     */
    public double getT() {
        return t;
    }
}
