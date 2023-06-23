/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.bullet;

import codex.jmeutil.es.ESAppState;
import com.jme3.app.Application;
import com.simsilica.es.EntityId;
import java.util.HashMap;

/**
 *
 * @author gary
 */
public class PhysicsRegistry extends ESAppState {

    HashMap<EntityId, PhysicsEntityMapping> registry = new HashMap<>();
    
    @Override
    protected void init(Application app) {}
    @Override
    protected void cleanup(Application app) {}
    @Override
    protected void onEnable() {}
    @Override
    protected void onDisable() {}
    
}
