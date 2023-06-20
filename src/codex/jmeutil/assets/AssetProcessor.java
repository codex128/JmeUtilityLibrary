/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.assets;

import codex.j3map.J3mapFactory;
import codex.j3map.processors.J3mapPropertyProcessor;
import com.jme3.asset.AssetManager;

/**
 *
 * @author gary
 */
public class AssetProcessor implements J3mapPropertyProcessor<AssetWrapper> {

    AssetCacheState assetCache;
    AssetManager assetManager;
    
    public AssetProcessor(AssetCacheState assetCache) {
        this.assetCache = assetCache;
    }
    public AssetProcessor(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    @Override
    public Class<AssetWrapper> type() {
        return AssetWrapper.class;
    }
    @Override
    public AssetWrapper process(String str) {
        String arg = J3mapFactory.getConstructorArguments(str, this);
        if (arg != null && arg.startsWith("\"") && arg.endsWith("\"")) {
            arg = arg.substring(1, arg.length()-1);
            if (assetCache != null) {
                return new AssetWrapper(arg, null);
            }
            else {
                return new AssetWrapper(arg, assetManager.loadAsset(arg));
            }
        }
        else {
            return null;
        }        
    }
    @Override
    public String[] export(AssetWrapper property) {
        return new String[] {getPropertyIdentifier()+"(\""+property.getName()+"\")"};
    }
    @Override
    public String getPropertyIdentifier() {
        return "asset";
    }
    @Override
    public AssetWrapper[] createArray(int length) {
        return new AssetWrapper[length];
    }
    
}
