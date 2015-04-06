

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
     * True anomaly.
     */
    private double v;
    
    /**
     * Constructs a new orbit.
     * @param e Eccentricity
     * @param a Semi-major axis.
     * @param i Inclination
     * @param om Longitude of the ascending node
     * @param w Argument of the periapsis.
     * @param v True anomaly.
     */
    public Orbit(double e, double a, double i, double om, double w, double v)
    {
        this.e = e;
        this.a = a;
        this.i = i;
        this.om = om;
        this.w = w;
        this.v = v;
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
        String s = "e: " + getE() + " a: " + getA() + " i: " + getI() + " om: " + getOm() + " w: " + getW() + " v: " + getV();
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
     * @return the v
     */
    public double getV() {
        return v;
    }
}
