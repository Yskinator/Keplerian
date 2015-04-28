
package keplerian.physics;

/**
 * Represents a rigid body.
 * @author Ville-Matti Tanninen
 */
public class RigidBody {
    
    /**
     * The mass of the object.
     */
    private double mass;
    
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
     * The direction the object is facing.
     */
    private Vector3d facing;
    
    
    /**
     * The angular velocity of the object.
     */
    private Vector3d angularVelocity;
    
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
     * @param facing The direction the object is facing towards.
     * @param angularVelocity Angular velocity of the object.
     * @param parent The celestial body the object is orbiting around.
     */
    public RigidBody(double mass, Vector3d relPos, Vector3d relVel, Vector3d facing, Vector3d angularVelocity, CelestialBody parent)
    {
        this.mass = mass;
        this.relPos = relPos;
        this.relVel = relVel;
        this.facing = facing;
        this.angularVelocity = angularVelocity;
        this.parent = parent;
        
        this.orbit = NO_ORBIT;
    }
    
    /**
     * Creates a new RigidBody based on an existing orbit.
     * @param mass Mass of the object.
     * @param facing The direction the object is facing towards.
     * @param angularVelocity The angular velocity of the object.
     * @param orbit The orbit the object is currently on.
     * @param parent The celestial body the object is orbiting around.
     */
    public RigidBody(double mass, Vector3d facing, Vector3d angularVelocity, Orbit orbit, CelestialBody parent)
    {
        this.mass = mass;
        this.facing = facing;
        this.angularVelocity = angularVelocity;
        this.orbit = orbit;
        this.parent = parent;
    }
    
    /**
     * Places the object in orbit.
     * @param t Current time.
     */
    public void toRails(double t)
    {
        double mParent = parent.getMass();
        this.orbit = TwoBodySolver.findOrbit(mParent, relPos, relVel, t);
    }
    /**
     * Removes the object from orbit and finds its velocity and position.
     * @param t The current time. Needed to calculate the position and velocity.
     */
    public void offRails(double t)
    {
        relVel = TwoBodySolver.findVelocity(orbit, t);
        relPos = TwoBodySolver.findPosition(orbit, t);
        
        this.orbit = NO_ORBIT;
    }

    /**
     * @return the mass
     */
    public double getMass() {
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
    
    public void setRelVel(Vector3d relVel)
    {
        this.relVel = relVel;
    }

    /**
     * @return the parent
     */
    public CelestialBody getParent() {
        return parent;
    }


    /**
     * @return the facing
     */
    public Vector3d getFacing() {
        return facing;
    }

    /**
     * @return the angularVelocity
     */
    public Vector3d getAngularVelocity() {
        return angularVelocity;
    }
}
