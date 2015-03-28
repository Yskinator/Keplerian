
package keplerian.physics;

/**
 * A 3 dimensional double precision vector.
 * New operations are implemented as they are needed.
 * @author Ville-Matti Tanninen
 */
class Vector3d {
    
    /**
     * Vector's x coordinate.
    */
    public double x;
    
    /**
     * Vector's y coordinate.
     */
    public double y;
    
    /**
     * Vector's z coordinate.
     */
    public double z;
    
    /**
     * Constructs a new vector.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public Vector3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Finds the unit vector corresponding to the vector.
     * @return Corresponding unit vector.
     */
    public Vector3d unitVector()
    {
        return Vector3d.mul(1/this.magn(), this);
    }
    
    /**
     * Multiplies the vector with a scalar.
     * @param scal Scalar value.
     * @param vec Vector.
     * @return Resulting vector.
     */
    public static Vector3d mul(double scal, Vector3d vec)
    {
        return new Vector3d(scal*vec.x, scal*vec.y, scal*vec.z);
    }
    /**
     * Multiplies the vector with a scalar.
     * @param vec Vector.
     * @param scal Scalar.
     * @return Resulting vector.
     */
    public static Vector3d mul(Vector3d vec, double scal)
    {
        return new Vector3d(scal*vec.x, scal*vec.y, scal*vec.z);
    }
    
    /**
     * Calculates the cross product of the two vectors.
     * @param u Vector number one.
     * @param v Vector number two.
     * @return Cross product of vectors one and two.
     */
    public static Vector3d crossProduct(Vector3d u, Vector3d v)
    {
        double x = u.y*v.z -u.z*v.y;
        double y = u.z*v.x - u.x*v.z;
        double z = u.x*v.y - u.y*v.x;
        
        return new Vector3d(x,y,z);
    }
    
    /**
     * Calculates the dot product of the two vectors.
     * @param u Vector number one.
     * @param v Vector number two.
     * @return Dot product of the two vectors.
     */
    public static double dotProduct(Vector3d u, Vector3d v)
    {
        double x = u.x*v.x;
        double y = u.y*v.y;
        double z = u.z*v.z;
        
        return x+y+z;
    }
    
    /**
     * Finds the magnitude of the vector.
     * @return The magnitude of the vector.
     */
    public double magn()
    {
        return Math.sqrt(x*x + y*y + z*z);
    }
}
