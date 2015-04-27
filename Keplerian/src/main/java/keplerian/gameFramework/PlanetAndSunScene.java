/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keplerian.gameFramework;

import java.util.ArrayList;
import keplerian.physics.CelestialBody;
import keplerian.physics.Orbit;
import keplerian.physics.RigidBody;
import keplerian.physics.TwoBodySolver;
import keplerian.physics.Vector3d;
import org.jsfml.graphics.Color;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;

/**
 *
 * @author Ville-Matti
 */
public class PlanetAndSunScene extends GUIScene{
    
    private Clock clock = new Clock();
    double t;
    private float tMult = 1000000;
    
    public PlanetAndSunScene()
    {
        super();
        entities = new ArrayList<Entity>();
        makeSun();
        makePlanet();
        plotPlanetOrbit();
        initEntities();
        clock.restart();
        t = 0;
        
    }
    
    @Override
    public void update()
    {
        //Update time.
        Time dt = clock.restart();
        t = t+dt.asSeconds()*tMult;
        
        Entity planet = entities.get(1);
        Orbit o = planet.getRigidBody().getOrbit();
        planet.position = TwoBodySolver.findPosition(o, t);
        //System.out.println(planet.position);
        super.update();
    }
    
    private void makeSun()
    {
        double m = 1000;
        Vector3d unimportant = new Vector3d(0,0,0);
        Entity sun = new Entity(new CelestialBody(m, unimportant, unimportant, CelestialBody.NO_ORBIT, CelestialBody.NO_PARENT));
        
        sun.setSpriteID("sun.png");
        sun.position = unimportant;
        entities.add(sun);
    }
    
    private void makePlanet()
    {
        double m = 100;
        Vector3d r = new Vector3d(0,1000,0);
        Vector3d vel = new Vector3d(1E-5, 1E-10, 0.0);
        Vector3d unimportant = new Vector3d(0,0,0);
        CelestialBody sun = (CelestialBody)entities.get(0).getRigidBody();
        Entity planet = new Entity(new RigidBody(m,r,vel,unimportant,unimportant,sun));
        planet.getRigidBody().toRails(0);
        
        planet.setSpriteID("planet.png");
        planet.position = r;
        entities.add(planet);
    }
    
    private void plotPlanetOrbit()
    {
        Entity orbit = new Entity(OrbitPlotter.plotOrbit(entities.get(1).getRigidBody().getOrbit(), Color.RED), new Vector3d(0,0,0));
        entities.add(orbit);
    }
}
