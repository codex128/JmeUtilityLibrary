/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.logic.Binary;
import com.simsilica.es.EntityComponent;

/**
 * Interface combining <code>EntityComponent</code> and <code>Binary</code>.
 * 
 * Allows components to participate in Logic.
 * 
 * @author gary
 */
public interface EntityBinaryComponent extends EntityComponent, Binary {}
