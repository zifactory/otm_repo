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
 * Namespace app.controllers Class HomeController.java
 *
 * @date 11/6/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers;

import app.models.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import zi.helper.ZHelper;
import zi.helper.ZHelperModel;
import zi.helper.ZUtilContent;

public class HomeController extends AppController {

    public void index() {
        String[] kategori = {"Kesehatan & Fitnes", "Matematika & Fisika", "Manajemen Sekolah"};
        String[] produk = {"camelot.png", "moderncombat.png", "robot.jpg",
                "tesla.png", "logo.png"};
        /*
         *   content.content_bean.judul
         content.content_bean.publisher
         content.content_bean.author
         content.content_bean.desc
         content.content_bean.point
         * */
        ZUtilContent vMap = ZUtilContent.getInstance();
        //per content
        vMap.contentCss("apps", "small", "square-cover");
        vMap.contentBean("judul", "Senja datang");
        vMap.contentBean("publisher", "black op");
        vMap.contentBean("author", "Pangeran Hitam");
        vMap.contentBean("desc", "Tuangan Puisi kala sepi");
        vMap.contentBean("point", 9000);
        vMap.getWrapperContent();
        vMap.contentCss("apps", "small", "square-cover");
        vMap.contentBean("judul", "kala itu selalu");
        vMap.contentBean("publisher", "green lantern");
        vMap.contentBean("author", "Pangeran Hijau");
        vMap.contentBean("desc", "Tuangan Puisi kala pup");
        vMap.contentBean("point", 5600);
        vMap.getWrapperContent();
        vMap.ContentBuilder("apps");
        vMap.contentCss("apps");
        vMap.contentCss("small");
        vMap.contentCss("square-cover");
        vMap.contentBean("judul", "Senja datang");
        vMap.contentBean("publisher", "black op");
        vMap.contentBean("author", "Pangeran Hitam");
        vMap.contentBean("desc", "Tuangan Puisi kala sepi");
        vMap.contentBean("point", 9000);
        vMap.getWrapperContent();
        vMap.contentCss("apps", "small", "square-cover");
        vMap.contentBean("judul", "kala itu selalu");
        vMap.contentBean("publisher", null);
        vMap.contentBean("author", "Suryakencana");
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
        ZHelper.logInfo(HomeController.class, String.valueOf(session().get("authuser")));
        view("authuser", session().get("authuser"));
        view("contents", vMap.getvVector());
        render();
    }

    public void cobaType() {
        /*
         *  {
         * "year": "1949",
         * "value": "All the Kings Men",
         * "tokens": [
         *   "All",
         *   "the",
         *   "Kings",
         *   "Men"
         *   ]
         *}
         * */
        LazyList<Category> cats = Category.findAll();
        /* List aArray = new ArrayList();
         for (Category cat : cats) {
         HashMap<String, Object> map = new HashMap<>();
         map.put("value", cat.get("keterangan"));
         map.put("tokens", cat.getString("keterangan").split(" "));
         map.put("id", cat.getId());
         aArray.add(map);
         }
         ObjectMapper mapper = new ObjectMapper();*/
        respond(ZHelperModel.typeaheadJS(cats, "keterangan", "keterangan")).contentType("text/json").status(200);
    }
}
