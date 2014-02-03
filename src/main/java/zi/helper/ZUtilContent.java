/**
 * Copyright 2013 Nanang Suryadi || nanang.ask@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * Namespace zi.helper Class ZUtilContent.java
 *
 * @date 12/31/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import lrf.pdf.PDFSerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ZUtilContent {
    
    private static ZUtilContent singleton;
    private ArrayList<Map> vVector;
    private ArrayList<Object> aArrayCss;
    private HashMap<String, Object> hHashBean;
    private ArrayList<Object> aWrapperContents;
    
    public static synchronized ZUtilContent getInstance() {
        try {
            if (null == singleton) {
                singleton = new ZUtilContent();
            }
            singleton.getvVector().removeAll(singleton.getvVector());
            singleton.getaWrapperContents().clear();
        } catch (Exception e) {
            ZHelper.logError(ZUtilContent.class, e.getMessage());
        }
        return singleton;
    }
    
    public static void convertPdfToEPub(String rootPath, String destPath) {
        File dirOut = new File(destPath);
        File root = new File(rootPath);
        PDFSerializer.dirDest = dirOut;
        PDFSerializer.dirOrig = root;
        PDFSerializer.recurse(root, "en");
    }

    /*
     *  ZUtilContent {
     "category_name" :nama
     "contents" :
     {
     -> "content_type" :app|video|games
     -> "content_css"  :ArrayList[ app, smalls, square-cover]
     -> "content_bean" :HasMap {
     content.content_bean.judul
     content.content_bean.publisher
     content.content_bean.author
     content.content_bean.desc
     content.content_bean.point
     }
     }
     }
     * */
    public ZUtilContent contentCss(Object... items) {
        if (items != null) {
            for (Object obj : items) {
                contentCss(obj);
            }
        }
        return this;
    }
    
    public ZUtilContent contentCss(Object item) {
        if (aArrayCss == null) {
            aArrayCss = new ArrayList<>();
        }
        aArrayCss.add(item);
        return this;
    }
    
    public ZUtilContent contentBean(String key, Object obj) {
        if (hHashBean == null) {
            hHashBean = new HashMap<>();
        }
        hHashBean.put(key, obj);
        return this;
    }
    
    public ZUtilContent getWrapperContent() {
        if (aWrapperContents == null) {
            aWrapperContents = new ArrayList<>();
        }
        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("content_css", aArrayCss);
        contentMap.put("content_bean", hHashBean);
        aWrapperContents.add(contentMap);
        hHashBean = null;
        aArrayCss = null;
        return this;
    }
    
    public ZUtilContent ContentBuilder(Object jenis) {
        //klo ga ad create
        if (vVector == null) {
            vVector = new ArrayList<>();
        }
        Map<String, Object> mapBuilder = new HashMap<>();
        mapBuilder.put("category_name", jenis);
        mapBuilder.put("contents", aWrapperContents);
        vVector.add(mapBuilder);
        aWrapperContents = null;
        return this;
    }
    
    public ArrayList<Map> getvVector() {
        return this.vVector;
    }
    
    public ArrayList<Object> getaArrayCss() {
        return aArrayCss;
    }
    
    public HashMap<String, Object> gethHashBean() {
        return hHashBean;
    }
    
    public ArrayList<Object> getaWrapperContents() {
        return aWrapperContents;
    }
}
