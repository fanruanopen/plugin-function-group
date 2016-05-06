package com.fr.solution.plugin.function.image;


import java.awt.*;
import java.util.Map;

/**
 * Created by richie on 16/5/4.
 */
public class ImageCachePool {

    private static Map<String, java.awt.Image> cacheMap = new LRUCache<String, java.awt.Image>(10);

    public static Image getImageByPath(String path) {
        return cacheMap.get(path);
    }

    public static void putImageToCache(String path, Image image) {
        cacheMap.put(path, image);
    }
}
