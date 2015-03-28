
package keplerian.physics;

/**
 * Represents a rigid body.
 * @author Ville-Matti Tanninen
 */
public class RigidBody {
    
    /**
     * The mass of the object.
     */
    private float mass;
    
    /**
     * Current orbit of the object.
     */
    private Orbit orbit;
    
    /**
     * Current velocity of the object, relative to the parent object.
     */
    private Vector3d relVel;
    
    /**
     * Current position of the object, relative to the parent object.
     */
    private Vector3d relPos;
    
    /**
     * Parent of the object, in other words whatever the celestial body it is currently orbitting.
     */
    private CelestialBody parent;
    
    /**
     * Constant used when object does not have an orbit, for instance when moving between
     * spheres of influence or accelerating.
     */
    public static final Orbit NO_ORBIT = null;
    
    /**
     * Constant used when object does not have a parent. Will be used for stationary objects, namely the
     * object everything else is orbiting around.
     */
    public static final CelestialBody NO_PARENT = null;
    
    /**
     * Creates a new RigidBody based on position and velocity.
     * @param mass Mass of the object.
     * @param relPos Position of the object relative to the parent.
     * @param relVel Velocity of the object relative to the parent.
     * @param parent The celestial body the object is orbiting around.
     */
    public RigidBody(int mass, Vector3d relPos, Vector3d relVel, CelestialBody parent)
    {
        this.mass = mass;
        this.relPos = relPos;
        this.relVel = relVel;
        this.parent = parent;
        
        this.orbit = NO_ORBIT;
    }
    
    /**
     * Creates a new RigidBody based on an existing orbit.
     * @param mass Mass of the object.
     * @param orbit The orbit the object is currently on.
     * @param parent The celestial body the object is orbiting around.
     */
    public RigidBody(int mass, Orbit orbit, CelestialBody parent)
    {
        this.mass = mass;
        this.orbit = orbit;
        this.parent = parent;
    }
    
    /**
     * Places the object in orbit.
     */
    public void toRails()
    {
        float mParent = parent.getMass();
        this.orbit = TwoBodySolver.findOrbit(mParent, relPos, relVel);
    }
    /**
     * Removes the object from orbit and finds its velocity and position.
     * @param t The current time. Needed to calculate the position and velocity.
     */
    public void offRails(float t)
    {
        relVel = orbit.findOrbitalVelocity(t);
        relPos = orbit.findPosition(t);
        
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
