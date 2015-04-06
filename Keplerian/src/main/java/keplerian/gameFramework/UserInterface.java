/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.gameFramework;

import java.util.ArrayList;

/**
 * Interface for a user interface.
 * @author Ville-Matti Tanninen
 */
public interface UserInterface {
    
    /**
     * Somehow shows the user all the relevant information.
     * @param entities List of things the user might want to know about.
     */
    public void show(ArrayList<Entity> entities);
}
