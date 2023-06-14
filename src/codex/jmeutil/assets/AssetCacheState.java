/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.assets;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import java.util.HashMap;

/**
 *
 * @author gary
 */
public class AssetCacheState extends BaseAppState {
    
    private static final String DEF_CACHE_TAG = "[default-asset-cache]";
    private static AssetCache defaultCache;
    
    protected AssetManager assetManager;
    HashMap<String, AssetCache> cacheMap = new HashMap<>();
    
    @Override
    protected void initialize(Application app) {
        assetManager = app.getAssetManager();
        if (defaultCache == null) {
            defaultCache = createCache(DEF_CACHE_TAG);
        }
        else {
            addCache(defaultCache);
        }
    }
    @Override
    protected void cleanup(Application app) {
        cacheMap.forEach((tag, cache) -> cache.clear());
        cacheMap.clear();
    }
    @Override
    protected void onEnable() {}
    @Override
    protected void onDisable() {}
    
    public <T> T fetchAsset(String name) {
        return fetchAsset(DEF_CACHE_TAG, name);
    }
    public <T> T fetchAsset(String tag, String name) {
        AssetCache cache = cacheMap.get(tag);
        if (cache == null) cache = createCache(tag);
        return cache.fetchAsset(name);
    }
    
    public AssetCache getCache() {
        return getCache(DEF_CACHE_TAG);
    }
    public AssetCache getCache(String tag) {
        return cacheMap.get(tag);
    }
    public void clearCache() {
        clearCache(DEF_CACHE_TAG);
    }
    public void clearCache(String tag) {
        AssetCache cache = cacheMap.remove(tag);
        if (cache != null) cache.clear();
    }
    
    protected HashMap<String, AssetCache> getCacheMap() {
        return cacheMap;
    }
    
    private AssetCache createCache(String tag) {
        AssetCache cache = new AssetCache(tag, assetManager);
        addCache(cache);
        return cache;
    }
    private void addCache(AssetCache cache) {
        if (cacheMap.putIfAbsent(cache.getTag(), cache) != null) {
            throw new IllegalStateException("Could not properly store asset cache!");
        }
    }
    
    public static String getDefaultCacheTag() {
        return DEF_CACHE_TAG;
    }
    public static AssetCache getDefaultCache() {
        return defaultCache;
    }
    
}
