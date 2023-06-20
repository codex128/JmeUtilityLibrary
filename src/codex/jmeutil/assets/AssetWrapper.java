/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.assets;

/**
 *
 * @author gary
 */
public class AssetWrapper {
    
    private String name;
    private Object asset;
    
    public AssetWrapper(String name) {
        this.name = name;
    }
    public AssetWrapper(String name, Object asset) {
        this.name = name;
        this.asset = asset;
    }
    
    public String getName() {
        return name;
    }
    public Object getAsset() {
        return asset;
    }
    public <T> T getAsset(Class<T> type) {
        if (isAssetType(type)) {
            return (T)asset;
        }
        return null;
    }
    public boolean isAssetCached() {
        return asset == null;
    }
    public boolean isAssetType(Class<?> type) {
        if (asset == null) return false;
        return type.isAssignableFrom(asset.getClass());
    }
    
    public <T> T getCachedAsset(AssetCacheState assetCache) {
        return assetCache.fetchAsset(name);
    }
    
}
