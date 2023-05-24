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
public class ModelManufactureTools extends EntityInfo {
	
	public final AssetManager assetManager;
	
	public ModelManufactureTools(EntityData ed, AssetManager assets, EntityId entity) {
		super(ed, entity);
		this.assetManager = assets;
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}
	
}
