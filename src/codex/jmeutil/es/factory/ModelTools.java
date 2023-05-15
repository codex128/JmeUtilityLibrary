/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import com.jme3.asset.AssetManager;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

/**
 *
 * @author gary
 */
public class ModelTools {
	
	public EntityData ed;
	public AssetManager assetManager;
	public EntityId entity;
	
	public ModelTools(EntityData ed, AssetManager assets, EntityId entity) {
		this.ed = ed;
		this.assetManager = assets;
		this.entity = entity;
	}
	
	public EntityData getEd() {
		return ed;
	}
	public AssetManager getAssetManager() {
		return assetManager;
	}
	public EntityId getEntity() {
		return entity;
	}
	
}
