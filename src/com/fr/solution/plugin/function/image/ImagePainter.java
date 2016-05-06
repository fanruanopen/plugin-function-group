package com.fr.solution.plugin.function.image;

import com.fr.base.AbstractPainter;
import com.fr.base.GraphHelper;
import com.fr.base.Style;
import com.fr.general.FRLogger;
import com.fr.general.IOUtils;
import com.fr.stable.Constants;
import com.fr.stable.StringUtils;

import java.awt.*;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by richie on 16/5/4.
 */
class ImagePainter extends AbstractPainter {

    private static final String REMOTE_HTTP = "http://";
    private static final String REMOTE_HTTPS = "https://";
    private static final String ABSOLUTE_FILE = "file:///";

    private String path;

    public ImagePainter(String path) {
        this.path = path;
    }

    @Override
    public void paint(Graphics g, int width, int height, int resolution, Style style) {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        Image image = ImageCachePool.getImageByPath(path);
        if (image == null) {
            image = readImageByPath(path);
        }
        if (image != null) {
            GraphHelper.paintImage(g , width, height, image, Constants.IMAGE_CENTER, style.getHorizontalAlignment(), style.getVerticalAlignment(), -1, -1);
        }
    }

    private Image readImageByPath(String path) {
        String lowerPath = path.toLowerCase();
        Image image = null;
        if (lowerPath.startsWith(REMOTE_HTTP) || lowerPath.startsWith(REMOTE_HTTPS)) {
            image = getImageFromNetByUrl(lowerPath);
        } else if (lowerPath.startsWith(ABSOLUTE_FILE)) {

        }
        if (image != null) {
            ImageCachePool.putImageToCache(path, image);
        }
        return image;
    }

    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    private Image getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream in = conn.getInputStream();//通过输入流获取图片数据
            return IOUtils.readImage(in);
        } catch (Exception e) {
            FRLogger.getLogger().error(e.getMessage(), e);
        }
        return null;
    }
}
