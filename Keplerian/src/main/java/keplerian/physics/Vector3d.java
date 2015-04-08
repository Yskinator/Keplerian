
package keplerian.physics;

import static java.lang.Double.NaN;

/**
 * A 3 dimensional double precision vector.
 * New operations are implemented as they are needed.
 * @author Ville-Matti Tanninen
 */
public class Vector3d {
    
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
    
    public boolean isUndefined()
    {
        if(this.x == NaN || this.y == NaN || this.z == NaN)
        {
            return true;
        }
        else
        {
            return false;
        }
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
     * Divides the vector with the scalar.
     * @param vec Vector,
     * @param scal Scalar.
     * @return Vector divided by scalar.
     */
    public static Vector3d div(Vector3d vec, double scal)
    {
        return new Vector3d(vec.x/scal, vec.y/scal, vec.z/scal);
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
    
    /**
     * Sums two vectors together.
     * @param u Vector one.
     * @param v Vector two.
     * @return Vector one + vector two.
     */
    public static Vector3d sum(Vector3d u, Vector3d v)
    {
        return new Vector3d(u.x+v.x, u.y+v.y, u.z+v.z);
    }
    
    /**
     * Subtracts vector two from vector one.
     * @param u Vector one.
     * @param v Vector two.
     * @return Vector one - Vector two.
     */
    public static Vector3d sub(Vector3d u, Vector3d v)
    {
        return new Vector3d(u.x-v.x, u.y-v.y, u.z-v.z);
    }
    
    @Override
    public String toString()
    {
        return "("+this.x+", " + this.y + ", " + this.z + ")";
    }
}
