/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

/**
 *
 * @author villtann
 */
class Vector3d {
    
    public double x;
    public double y;
    public double z;
    
    public Vector3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3d unitVector()
    {
        return Vector3d.mul(1/this.magn(), this);
    }
    
    public static Vector3d mul(double a, Vector3d b)
    {
        return new Vector3d(a*b.x, a*b.y, a*b.z);
    }
    
    public static Vector3d mul(Vector3d b, double a)
    {
        return new Vector3d(a*b.x, a*b.y, a*b.z);
    }
    
    public static Vector3d crossProduct(Vector3d u, Vector3d v)
    {
        double x = u.y*v.z -u.z*v.y;
        double y = u.z*v.x - u.x*v.z;
        double z = u.x*v.y - u.y*v.x;
        
        return new Vector3d(x,y,z);
    }
    
    public static double dotProduct(Vector3d u, Vector3d v)
    {
        double x = u.x*v.x;
        double y = u.y*v.y;
        double z = u.z*v.z;
        
        return x+y+z;
    }
    
    public double magn()
    {
        return Math.sqrt(x*x + y*y + z*z);
    }
}
