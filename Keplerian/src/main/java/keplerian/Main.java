package keplerian;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;


public class Main {
    private static RenderWindow window;

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


    /**
     * Creates the window for the game.
     */
    private static RenderWindow createWindow() {
        RenderWindow w = new RenderWindow();
        w.create(VideoMode.getFullscreenModes()[0], "Keplerian", WindowStyle.DEFAULT);
        return w;
    }
}
