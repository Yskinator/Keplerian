package keplerian;

import java.util.Scanner;
import keplerian.gameFramework.GUI;
import keplerian.gameFramework.PlanetAndSunScene;
import keplerian.gameFramework.Scene;
import static keplerian.gameFramework.Scene.NO_SCENE;
import keplerian.physics.Orbit;
import keplerian.physics.TwoBodySolver;
import keplerian.physics.Vector3d;

/*
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;
*/

public class Main {
    
    private static Scene scene = NO_SCENE;
    
    public static void main(String[] args)
    {
        scene = new PlanetAndSunScene();
        while(scene != NO_SCENE)
        {
            //Gets input, updates the game, shows the result.
            scene.processInput();
            scene.update();
            scene.show();
            
            //Changes scenes if necessary.
            scene = scene.getCurrScene();
        }
    }
    
    
    /*
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the mass of the central object in kilograms.");
        double mass = scan.nextDouble();
        System.out.println("Enter the position of the object relative to the central object.");
        System.out.print("x: ");
        double x = scan.nextDouble();
        System.out.print("y: ");
        double y = scan.nextDouble();
        
        Vector3d r = new Vector3d(x,y,0);
        
        System.out.println("Enter the velocity of the object relative to the central object.");
        System.out.print("x: ");
        double xVel = scan.nextDouble();
        System.out.print("y: ");
        double yVel = scan.nextDouble();
        
        Vector3d vel = new Vector3d(xVel, yVel, 0);
        
        Orbit orbit = TwoBodySolver.findOrbit(mass, r, vel, 0);
        System.out.println("Resulting orbit: " + orbit);
        
    }*/
    
    /*

    public static void main(String[] args)
    {
           window = createWindow();
           while(window.isOpen()) 
           {
                window.clear(Color.BLACK);
                window.display();
                
                for(Event event : window.pollEvents()) {
                    if(event.type == Event.Type.CLOSED) {
                        window.close();
                    }
                }
           }
    }

    */
    /**
     * Creates the window for the game.
     */
    /*
    private static RenderWindow createWindow() {
        RenderWindow w = new RenderWindow();
        w.create(VideoMode.getFullscreenModes()[0], "Keplerian", WindowStyle.DEFAULT);
        return w;
    }*/
}
