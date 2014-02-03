/**
 * Copyright 2013 Nanang Suryadi || nanang.ask@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Namespace app.controllers.contents
 * Class InventoryController.java
 * @date 12/19/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.contents;

import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;
import zi.helper.ZUtilContent;

import java.util.HashMap;
import java.util.Map;

public class InventoryController extends AppController {

    /* ===============================================================================
       GET     /1/content/id/dashboard               i005             display a list of all content user / publisher
       GET     /1/content/id/{context}/add           a001             return an HTML form for creating a new {context} content
       POST    /1/content/id/{context}               s003             create a new {context} content .save
       GET     /1/content/id/{context}/{id_content}  v005             display a specific {context} content
       DELETE  /1/content/id/{context}/{id_content}  d011             delete a specific {context} content
       GET     /1/content/id/{context}               index            display a list of {context} content user / publisher
       ==================================================================================*/

    /*
    *  GET     /1/content/{ID}/{context}
    * */
    public void index() {
        if (isXhr()) {
//            logInfo(param("ID") + "::" + param("context"));
//            view("greeting", param("context"));
//            render("/greeting/index").noLayout();
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
            vMap.contentCss("apps", "small", "square-cover");
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
            Map<String, Object> action = new HashMap<>();
            action.put("menu_content", true);
            view("actUser", action);
            view("contents", vMap.getvVector());
            render("/home/index").noLayout();
        } else render("/system/404").noLayout();
    }

    /*
    *  GET     /1/content/id/dashboard
    * */
    public void i005() {
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
        vMap.contentCss("apps", "small", "square-cover");
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
        Map<String, Object> action = new HashMap<>();
        action.put("menu_content", true);
        view("actUser", action);
        view("contents", vMap.getvVector());
        render("/home/index");
    }

    /*
    *  GET     /1/content/id/{context}/add
    * */
    public void a001() {
        render().noLayout();
    }

    /*
    *  POST    /1/content/id/{context}
    * */
    @POST
    public void s003() {
        //!exist content folder buat baru sesuai ID Content
        //ZHelper.content_mkdir("", getRealPath(""));
    }

    /*
    *  DELETE  /1/content/id/{context}/{id_content}
    * */
    public void d011() {

    }
}
