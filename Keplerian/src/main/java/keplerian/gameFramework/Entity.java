/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.gameFramework;

import keplerian.physics.RigidBody;
import keplerian.physics.Vector3d;
import org.jsfml.graphics.Image;

/**
 * Anything the user might be interested in.
 * @author Ville-Matti Tanninen
 */
public class Entity {
    /**
     * A constant used for entities without rigid bodies.
     */
    public final static RigidBody NO_RIGIDBODY = null;
    
    /**
     * A constant used for entities without sprites, in other words entities that cannot be drawn.
     */
    public final static String NO_SPRITE = "NO_SPRITE";
    
    /**
     * A constant used for entities without sprites, in other words entities that cannot be drawn.
     */
    public final static String BUILT_IN_IMAGE = "BUILT_IN_IMAGE";
    
    /**
     * The rigid body of the entity, if it has one.
     */
    private RigidBody rigidBody;
    
    /**
     * ID used to find the correct sprite for the entity, if it has one.
     */
    private String spriteID = NO_SPRITE;
    
    public Vector3d position;
    
    public Image image;
    
    /**
     * Constructs a new entity with a rigid body.
     * @param rigidBody The rigidBody of the entity.
     */
    public Entity(RigidBody rigidBody) {
        this.rigidBody = rigidBody;
    }
    
    public Entity(Image image, Vector3d pos) {
        this.position = pos;
        this.image = image;
        this.spriteID = BUILT_IN_IMAGE;
    }
    
    
    
    
    
    /**
     * Used to check if the entity has a rigid body.
     * @return Whether the entity has a rigid body or not.
     */
    public boolean hasRigidBody()
    {
        if(this.getRigidBody() != NO_RIGIDBODY)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Used to check if the entity has a sprite.
     * @return Whether the entity has a sprite of not.
     */
    public boolean hasSprite()
    {
        if(this.getSpriteID().equals(NO_SPRITE))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * @return the rigidBody
     */
    public RigidBody getRigidBody() {
        return rigidBody;
    }

    /**
     * @return the spriteID
     */
    public String getSpriteID() {
        return spriteID;
    }

    /**
     * @param spriteID the spriteID to set
     */
    public void setSpriteID(String spriteID) {
        this.spriteID = spriteID;
    }
}
