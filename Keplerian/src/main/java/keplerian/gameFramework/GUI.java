/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keplerian.gameFramework;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import keplerian.physics.Vector3d;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

/**
 *
 * @author Ville-Matti
 */
public class GUI implements UserInterface {
    
    /**
     * Window used to display things.
     */
    private RenderWindow window;
    
    private HashMap<String, Sprite> sprites = new HashMap<>();
    
    /**
     * HashMap containing all the textures used by the Scene.
     * When a texture is used, it's fetched from here.
     */
    private HashMap<String, Texture> textures;
    private PlanetAndSunScene scene;
    
    public void init(ArrayList<Entity> entities, PlanetAndSunScene scene)
    {
        textures = new HashMap<>();
        this.scene = scene;
        window = new RenderWindow();
        window.create(VideoMode.getFullscreenModes()[0], "Keplerian", WindowStyle.DEFAULT);
        View view = new View();
        view.setSize(10000, 10000);
        view.setCenter(Vector2f.ZERO);
        window.setView(view);
        for(Entity e : entities)
        {
            if(e.hasSprite())
            {
                boolean fromFile = false;
                if(e.getSpriteID() == Entity.BUILT_IN_IMAGE)
                {
                    int i = 0;
                    while(sprites.containsKey(e.getSpriteID() + i))
                    {
                        i++;
                    }
                    convertToTexture(e.getSpriteID(), e.image);
                    
                }
                else
                {
                    loadImage(e.getSpriteID());
                    fromFile = true;
                }
                //If image loaded successfully
                if(textures.containsKey(e.getSpriteID()))
                {
                    Sprite s = new Sprite(textures.get(e.getSpriteID()));
                    s.setPosition(new Vector2f((float)e.position.x, (float)e.position.y));
                    s.setOrigin(Vector2f.div(new Vector2f(s.getTexture().getSize()),2));
                    if(!fromFile)
                    {
                        s.scale(new Vector2f(3,3));
                    }
                    sprites.put(e.getSpriteID(), s);
                }
            }
        }
    }
    
    public void addEntity(Entity e)
    {
        if(e.hasSprite())
        {
            loadImage(e.getSpriteID());
            //If image loaded successfully
            if(textures.containsKey(e.getSpriteID()))
            {
                Sprite s = new Sprite(textures.get(e.getSpriteID()));
                s.setPosition(new Vector2f((float)e.position.x, (float)e.position.y));
            }
        }
    }
    

    /**
     * Loads a new texture into memory.
     * @param name Name of the texture to be loaded.
     */
    public void loadImage(String name)
    {
        if(!textures.containsKey(name))
        {
            Texture tx = new Texture();
            try
            {
                tx.loadFromFile(Paths.get(name));
                textures.put(name, tx);
            } catch(Exception e)
            {
                //Image failed to load!
                System.out.println("Image loading error! " + name);
            }
        }
    }
    
    public void convertToTexture(String name, Image image)
    {
        
        Texture tx = new Texture();
        try
        {
            tx.loadFromImage(image);
            textures.put(name, tx);
        } catch(Exception e)
        {
            //Failed to convert to texture.
            System.out.println("Texture conversion failed! " + name);
        }
        
    }
    
    public boolean windowClosed()
    {
        return !window.isOpen();
    }
    
    /**
     * Closes the window.
     */
    public void close()
    {
        window.close();
    }

    @Override
    public void show(ArrayList<Entity> entities) {
        if(window.isOpen()) 
        {
            window.clear(Color.BLACK);
            for(int i = entities.size()-1; i >= 0; i--)
            {
                Entity e = entities.get(i);
                if(sprites.containsKey(e.getSpriteID()))
                {
                    window.draw(sprites.get(e.getSpriteID()));
                }
            }
            window.display();
        }
    }
    
    /**
     * Moves the visible sprites to match the current positions of the entities.
     * @param entities List of all entities.
     */
    public void updateSpritePositions(ArrayList<Entity> entities)
    {
        for(Entity e : entities)
        {
            if(sprites.containsKey(e.getSpriteID()))
            {
                Sprite s = sprites.get(e.getSpriteID());
                s.setPosition(new Vector2f((float)e.position.x, (float)e.position.y));
            }
        }
    }

    @Override
    public void getInput() {
        for(Event e : window.pollEvents())
        {
            if(e.type == Event.Type.KEY_PRESSED)
            {
                KeyEvent ke = e.asKeyEvent();
                if(ke.key == Key.ESCAPE)
                {
                    close();
                }
                if(ke.key == Key.Z)
                {
                    scene.multTMult(1.2f);
                }
                if(ke.key == Key.X)
                {
                    scene.multTMult(0.8f);
                }
                if(ke.key == Key.R)
                {
                    scene.multTMult(-1.0f);
                }
                if(ke.key == Key.PERIOD)
                {
                    window.getView();
                    View view = new View(window.getView().getCenter(), window.getView().getSize());
                    view.zoom(1.2f);
                    window.setView(view);
                }
                if(ke.key == Key.COMMA)
                {
                    window.getView();
                    View view = new View(window.getView().getCenter(), window.getView().getSize());
                    view.zoom(0.8f);
                    window.setView(view);
                }
            }
            if(e.type == Event.Type.CLOSED)
            {
                close();
            }
        }
    }
}
