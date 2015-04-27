/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keplerian.gameFramework;

import java.util.ArrayList;

/**
 *
 * @author Ville-Matti
 */
public abstract class Scene {
    
    public static final Scene NO_SCENE = null;
    
    
    /**
    * Current scene - used when moving from scene to scene,
    */
    protected Scene currScene;
    
    /**
     * User Interface currently in use.Each scene must have SOME way of 
     * communicating with the user, but the exact nature is optional.
     */
    protected UserInterface UI;
    
    /**
     * List of all entities in the scene.
     */
    protected ArrayList<Entity> entities;
    
    public Scene()
    {
        currScene = this;
    }
    
    
    /**
     * Returns the current scene.
     * @return Current scene.
     */
    public Scene getCurrScene()
    {
        return this.currScene;
    }
    
    /**
     * Updates the game, whatever that does.
     */
    public abstract void update();
    
    /**
     * Gets user input and does things with it.
     */
    public abstract void processInput();
    
    /**
     * Displays results back to the user.
     */
    public abstract void show();
    
    /**
     * Changes scene from current scene to the given scene.
     * @param nextScene Scene to move to.
     */
     public void toScene(Scene nextScene)
     {
            this.currScene = nextScene;
     }
     
     public abstract void exit();
}
