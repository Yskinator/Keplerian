/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keplerian.gameFramework;

import keplerian.physics.RigidBody;

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
     * The rigid body of the entity, if it has one.
     */
    private RigidBody rigidBody;

    /**
     * Constructs a new entity with a rigid body.
     * @param rigidBody The rigidBody of the entity.
     */
    public Entity(RigidBody rigidBody) {
        this.rigidBody = rigidBody;
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
     * @return the rigidBody
     */
    public RigidBody getRigidBody() {
        return rigidBody;
    }
}
