/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keplerian.gameFramework;

/**
 *
 * @author Ville-Matti
 */
public class GUIScene extends Scene {
    
    /**
     * Constructs a new GameScene.
     */
    public GUIScene()
    {
        this.UI = new GUI();
    }
    
    protected void initEntities()
    {
        GUI gui = (GUI) UI;
        gui.init(entities, (PlanetAndSunScene)this);
    }
    
    @Override
    public void update() {
        GUI gui = (GUI) UI;
        gui.updateSpritePositions(entities);
        
    }

    @Override
    public void processInput() {
        GUI gui = (GUI) UI;
        gui.getInput();
        if(gui.windowClosed())
        {
            exit();
        }
    }

    @Override
    public void show() {
        GUI gui = (GUI) UI;
        gui.show(entities);
    }

    @Override
    public void exit() {
        GUI gui = (GUI) UI;
        gui.close();
        currScene = NO_SCENE;
    }
    
}
