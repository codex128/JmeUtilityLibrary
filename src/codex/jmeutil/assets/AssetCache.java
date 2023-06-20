/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.assets;

import com.jme3.asset.AssetManager;
import java.util.HashMap;

/**
 *
 * @author gary
 */
public class AssetCache extends HashMap<String, Object> {
    
    private String tag;
    protected AssetManager assetManager;
    
    public AssetCache(String tag, AssetManager assetManager) {
        this.tag = tag;
        this.assetManager = assetManager;
    }
    
    public void loadAsset(String name) {
        Object asset = get(name);
        if (asset == null) {
            asset = assetManager.loadAsset(name);
            putIfAbsent(name, asset);
        }
    }
    public <T> T fetchAsset(String name) {
        Object asset = get(name);
        if (asset == null) return createAsset(name);
        else return (T)asset;
    }
    private <T> T createAsset(String name) {
        Object asset = assetManager.loadAsset(name);
        putIfAbsent(name, asset);
        return (T)asset;
    }
    
    public boolean containsAsset(String name) {
        return containsKey(name);
    }
    public String getTag() {
        return tag;
    }
    
}
