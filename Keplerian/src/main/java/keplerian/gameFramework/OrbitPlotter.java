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
    private static final int dotCount = 1000000;
    
    public static Image plotOrbit(Orbit o, Color c)
    {
        Image graph = new Image();
        graph.create(10000,10000, Color.TRANSPARENT);
        
        double T = TwoBodySolver.findOrbitalPeriod(o);
        double t = 0;
        Vector3d pos;
        for(int i = 0; i < dotCount; i++)
        {
            pos = TwoBodySolver.findPosition(o, t);
            graph.setPixel((int)pos.x+5000, (int)pos.y+5000, c);
            t = t + T/dotCount;
        }
        return graph;
    }
}
