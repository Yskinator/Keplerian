/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.physics;

import static keplerian.physics.Helper.closeEnough;
import static keplerian.physics.Helper.toRadians;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author villtann
 */
public class RigidBodyTest {
    
    RigidBody rb;
    CelestialBody cb;
    double mass = 1.98891691172467E+30;
    Vector3d pos = new Vector3d(617244712357.9814, -431694791368.0269, -12036457087.000256);
    Vector3d vel = new Vector3d(7320.00000000049, 11328.999999999676, -211.00000000002197);
    Orbit oc = new Orbit(0.04842547228152425, 778194564622.5737, toRadians(1.3051851018817964), toRadians(100.4943151229807), toRadians(275.3654041542324), toRadians(313.38300121665685), 0, 0);
    Vector3d facing = new Vector3d(0,-10,-100);
    Vector3d angularVelocity = new Vector3d(-100,-10,0);
    CelestialBody parent = null;
    
    public RigidBodyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cb = new CelestialBody(mass, facing, angularVelocity, RigidBody.NO_ORBIT, parent);
        rb = new RigidBody(mass, pos, vel, facing, angularVelocity, cb);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void toRailsWorks()
    {
        rb.toRails(0);
        
        String error = "toRails works incorrectly. Received " + rb.getOrbit() + ", expected " + oc;
        assertTrue(error, closeEnough(rb.getOrbit(), oc));
    }
    
    /* offRails not implemented.
    @Test 
    public void offRailsWorks()
    {
        rb.toRails(0);
        rb.offRails(0);
    }*/
    
    @Test
    public void massGetterWorks()
    {
        assertTrue(mass == rb.getMass());
    }
    
    @Test
    public void massSetterWorks()
    {
        rb.setMass(-1);
        assertTrue(rb.getMass() == -1);
    }
    
    @Test
    public void orbitGetterWorks()
    {
        assertTrue(rb.getOrbit() == RigidBody.NO_ORBIT);
    }
    
    @Test
    public void velocityGetterWorks()
    {
        assertTrue(rb.getRelVel() == vel);
    }
    
    
    @Test
    public void positionGetterWorks()
    {
        assertTrue(rb.getRelPos() == pos);
    }
    
    @Test
    public void parentGetterWorks()
    {
        assertTrue(rb.getParent() == cb);
    }
    
    @Test
    public void facingGetterWorks()
    {
        assertTrue(rb.getFacing() == facing);
    }
    
    @Test
    public void angularVelocityGetterWorks()
    {
        assertTrue(rb.getAngularVelocity() == angularVelocity);
    }
    
}
