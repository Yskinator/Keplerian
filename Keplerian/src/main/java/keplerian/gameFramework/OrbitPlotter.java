/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keplerian.gameFramework;

import keplerian.physics.Orbit;
import keplerian.physics.TwoBodySolver;
import keplerian.physics.Vector3d;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;

/**
 *
 * @author Ville-Matti
 */
public class OrbitPlotter {
    private static final int dotCount = 100000;
    
    public static Image plotOrbit(Orbit o, Color c)
    {
        Image graph = new Image();
        graph.create(5000,5000, Color.TRANSPARENT);
        
        double T = TwoBodySolver.findOrbitalPeriod(o);
        double t = 0;
        Vector3d pos;
        for(int i = 0; i < dotCount; i++)
        {
            pos = TwoBodySolver.findPosition(o, t);
            int x = (int)pos.x/5+2500;
            int y = (int)pos.y/5+2500;
            if(x < 5000 && x > 0 && y < 5000 && y > 0)
            {
                graph.setPixel(x, y, c);
            }
            
            t = t + T/dotCount;
        }
        return graph;
    }
}
