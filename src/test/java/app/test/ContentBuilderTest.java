/**
 * Copyright 2014 Nanang Suryadi || nanang.ask@gmail.com
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
 * Namespace app.test Class ContentBuilderTest.java
 *
 * @date 1/2/14
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.Test;
import zi.helper.ZUtilContent;
import zi.helper.test.ActiveJDBCTest;

import java.util.Map;

public class ContentBuilderTest extends ActiveJDBCTest {

    @Test
    public void contentBuilder() {
        ArrayList<Map> vMap = ZUtilContent.getInstance()
                //                .ContentBuilder("videos", Kab.findAll())
                //                .ContentBuilder("app", Category.findAll())
                .getvVector();
        // System.out.println(Arrays.toString(vMap.toArray()));
    }

    @Test
    public void getContent() {
        /**
         * content.content_bean.judul content.content_bean.publisher
         * content.content_bean.author content.content_bean.desc
         * content.content_bean.point
         *
         */
        ZUtilContent vMap = ZUtilContent.getInstance();
        //per content
        vMap.contentCss("apps", "small", "square-box");
        vMap.contentBean("judul", "Senja datang");
        vMap.contentBean("publisher", "black op");
        vMap.contentBean("author", "Pangeran Hitam");
        vMap.contentBean("desc", "Tuangan Puisi kala sepi");
        vMap.contentBean("point", 9000);
        vMap.getWrapperContent();
        vMap.contentCss("apps", "small", "square-box");
        vMap.contentBean("judul", "kala itu selalu");
        vMap.contentBean("publisher", "green lantern");
        vMap.contentBean("author", "Pangeran Hijau");
        vMap.contentBean("desc", "Tuangan Puisi kala pup");
        vMap.contentBean("point", 5600);
        vMap.getWrapperContent();
        vMap.ContentBuilder("apps");
        vMap.contentCss("apps", "small", "square-box");
        vMap.contentBean("judul", "Senja datang");
        vMap.contentBean("publisher", "black op");
        vMap.contentBean("author", "Pangeran Hitam");
        vMap.contentBean("desc", "Tuangan Puisi kala sepi");
        vMap.contentBean("point", 9000);
        vMap.getWrapperContent();
        vMap.contentCss("apps", "small", "square-box");
        vMap.contentBean("judul", "kala itu selalu");
        vMap.contentBean("publisher", "green lantern");
        vMap.contentBean("author", "Pangeran Hijau");
        vMap.contentBean("desc", "Tuangan Puisi kala pup");
        vMap.contentBean("point", 5600);
        vMap.getWrapperContent();
        vMap.ContentBuilder("videos");
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(vMap.getvVector()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
