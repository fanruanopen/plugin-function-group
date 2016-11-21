package com.fr.solution.plugin.function;

import com.fr.general.FArray;
import com.fr.general.FRLogger;
import com.fr.general.GeneralUtils;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import com.fr.stable.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * HTML元素查找器
 */
public class HtmlFinder extends AbstractSolutionFunction {

    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 2) {
            return Primitive.ERROR_VALUE;
        }
        String source = GeneralUtils.objectToString(args[0]);
        if (StringUtils.isEmpty(source)) {
            return Primitive.ERROR_VALUE;
        }
        String selector = GeneralUtils.objectToString(args[1]);
        Document doc = null;
        try {
            doc = Jsoup.connect(source).get();
        } catch (IOException e) {
            FRLogger.getLogger().error(e.getMessage(), e);
        }
        if (doc != null) {
            FArray<String> result = new FArray<String>();
            Elements elements = doc.select(selector);
            for (Element element : elements) {
                result.add(element.text());
            }
            return result;
        }
        return Primitive.ERROR_VALUE;

    }

    public static void main(String... args) {
        HtmlFinder finder = new HtmlFinder();
        Object t = finder.solve(new Object[]{"http://www.baidu.com", "a[href]"});
        System.out.println(t);
    }
}
