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
 * Namespace app.config Class RouteConfig.java
 *
 * @date 10/27/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.config;

import app.controllers.access.LoginController;
import app.controllers.accounts.ProfilesController;
import app.controllers.contents.BooksController;
import app.controllers.contents.InventoryController;
import app.controllers.contents.VideosController;
import app.controllers.setting.*;
import org.javalite.activeweb.AbstractRouteConfig;
import org.javalite.activeweb.AppContext;

public class RouteConfig extends AbstractRouteConfig {

    public void init(AppContext appContext) {
        //Disable Route Default
        route("/{controller}/{action}").to(Route404Controller.class).get().post();
        //route("/myposts").to(PostsController.class);
//        route("/{action}/buku/{id}").to(BooksController.class);
//        route("/{action}/{controller}/{id}").get();
//        route("/{action}/listbuku").to(BookContentsController.class).get();
        /* route unique untuk level login 0 -> user , 1 -> publisher , 3 -> administrator
         *  InventoryController Koleksi buat publisher / Author buat karya2nya
         *  Collection Contents yg sudah dibeli oleh user
         * */
        /* ===============================================================================
         GET     /books                 index           display a list of all books
         GET     /books/new_form        new_form        return an HTML form for creating a new book
         POST    /books                 create          create a new book
         GET     /books/id              show            display a specific book
         GET     /books/id/edit_form    edit_form       return an HTML form for editing a books
         PUT     /books/id              update          update a specific book
         DELETE  /books/id              destroy         delete a specific book
         ==================================================================================*/
        route("/0/{ID}").to(ProfilesController.class).action("index").get();
        route("/0/{action}").to(ProfilesController.class).post();

        route("/x").to(LoginController.class).action("logout").post();
        route("/3").to(LoginController.class).action("index").get();
        route("/3/l/{action}").to(LoginController.class).post();

        /* ===============================================================================
         GET     /3/cats                index            display a list of all category
         GET     /3/cats/add            a001             return an HTML form for creating a new category
         POST    /3/cats                s003             create a new category .save
         GET     /3/cats/id             v005             display a specific category
         DELETE  /3/cats/id             d011             delete a specific category
         ==================================================================================*/
        // list buat master gak perlu ID user ...
        route("/3/cats").to(CategoryController.class).action("index").get();
        route("/3/cats/add").to(CategoryController.class).action("a001").get();
        route("/3/cats").to(CategoryController.class).action("s003").post();
        route("/3/cats/jp").to(CategoryController.class).action("dtbl").get();
        route("/3/cats/{ID}").to(CategoryController.class).action("d011").delete();
        route("/3/cats/{ID}").to(CategoryController.class).action("v005").get();

        /* ===============================================================================
         GET     /3/grp                index            display a list of all group
         GET     /3/grp/add            a001             return an HTML form for creating a new group
         POST    /3/grp                s003             create a new group .save
         GET     /3/grp/id             v005             display a specific group
         DELETE  /3/grp/id             d011             delete a specific group
         ==================================================================================*/
        // list buat master gak perlu ID user ...
        route("/3/grp").to(GroupController.class).action("index").get();
        route("/3/grp/add").to(GroupController.class).action("a001").get();
        route("/3/grp").to(GroupController.class).action("s003").post();
        route("/3/grp/jp").to(GroupController.class).action("dtbl").get();
        route("/3/grp/{ID}").to(GroupController.class).action("d011").delete();
        route("/3/grp/{ID}").to(GroupController.class).action("v005").get();

        /* ===============================================================================
         GET     /3/kt                index            display a list of all kota
         GET     /3/kt/add            a001             return an HTML form for creating a new kota
         POST    /3/kt                s003             create a new kota .save
         GET     /3/kt/id             v005             display a specific kota
         DELETE  /3/kt/id             d011             delete a specific kota
         ==================================================================================*/
        // list buat master gak perlu ID user ...
        route("/3/kt").to(KotaController.class).action("index").get();
        route("/3/kt/add").to(KotaController.class).action("a001").get();
        route("/3/kt").to(KotaController.class).action("s003").post();
        route("/3/kt/jp").to(KotaController.class).action("dtbl").get();
        route("/3/kt/{ID}").to(KotaController.class).action("d011").delete();
        route("/3/kt/{ID}").to(KotaController.class).action("v005").get();

        /* ===============================================================================
         GET     /3/kb                index            display a list of all kabupaten
         GET     /3/kb/add            a001             return an HTML form for creating a new kabupaten
         POST    /3/kb                s003             create a new kabupaten .save
         GET     /3/kb/id             v005             display a specific kabupaten
         DELETE  /3/kb/id             d011             delete a specific kabupaten
         ==================================================================================*/
        // list buat master gak perlu ID user ...
        route("/3/kb").to(KabController.class).action("index").get();
        route("/3/kb/add").to(KabController.class).action("a001").get();
        route("/3/kb").to(KabController.class).action("s003").post();
        route("/3/kb/jp").to(KabController.class).action("dtbl").get();
        route("/3/kb/{ID}").to(KabController.class).action("d011").delete();
        route("/3/kb/{ID}").to(KabController.class).action("v005").get();

        /* ===============================================================================
         GET     /1/content/id/dashboard               i005             display a list of all content user / publisher
         GET     /1/content/id/{context}/add           a001             return an HTML form for creating a new {context} content
         POST    /1/content/id/{context}               s003             create a new {context} content .save
         GET     /1/content/id/{context}/{id_content}  v005             display a specific {context} content
         DELETE  /1/content/id/{context}/{id_content}  d011             delete a specific {context} content
         GET     /1/content/id/{context}               index            display a list of {context} content user / publisher
         ==================================================================================*/
        route("/1/content/{ID}/dashboard").to(InventoryController.class).action("i005").get();
        route("/1/content/{ID}/{context}/add").to(InventoryController.class).action("a001").get();
        route("/1/content/{ID}/{context}").to(InventoryController.class).action("s003").post();
        route("/1/content/{ID}/{context}/{id_c}").to(InventoryController.class).action("s003").post();
        route("/1/content/{ID}/{context}/{id_c}").to(InventoryController.class).action("d011").delete();
        route("/1/content/{ID}/{context}").to(InventoryController.class).action("index").get();

        /* ===============================================================================
         GET     /1/content/id/{context}/add           a001             return an HTML form for creating a new {context} content
         POST    /1/content/{context}                  s003             create a new {context} content .save
         GET     /1/content/id/{context}               v005             display a specific {context} content
         GET     /1/content/jp                         dtbl             JSONP datatable ajax
         DELETE  /1/content/id/{context}/{id_content}  d011             delete a specific {context} content
         GET     /1/content/{context}                  index            display a list of {context} content user / publisher
         ==================================================================================*/
        route("/1/c/book/add").to(BooksController.class).action("a001").get();
        route("/1/c/book").to(BooksController.class).action("s003").post();
        route("/1/c/{ID}/book").to(BooksController.class).action("v005").get();
        route("/1/c/book/jp").to(BooksController.class).action("dtbl").get();
        route("/1/c/{ID}/book").to(BooksController.class).action("d011").delete();
        route("/1/c/book").to(BooksController.class).action("index").get();

         /* ===============================================================================
         GET     /1/content/id/{context}/add           a001             return an HTML form for creating a new {context} content
         POST    /1/content/{context}                  s003             create a new {context} content .save
         GET     /1/content/id/{context}               v005             display a specific {context} content
         GET     /1/content/jp                         dtbl             JSONP datatable ajax
         DELETE  /1/content/id/{context}/{id_content}  d011             delete a specific {context} content
         GET     /1/content/{context}                  index            display a list of {context} content user / publisher
         ==================================================================================*/
        route("/1/c/video/add").to(VideosController.class).action("a001").get();
        route("/1/c/video").to(VideosController.class).action("s003").post();
        route("/1/c/{ID}/video").to(VideosController.class).action("v005").get();
        route("/1/c/video/jp").to(VideosController.class).action("dtbl").get();
        route("/1/c/{ID}/video").to(VideosController.class).action("d011").delete();
        route("/1/c/video").to(VideosController.class).action("index").get();

        //route khusus utk web service JSonP ga perlu login utk akses data ini
        route("/s/pubs").to(JsonpController.class).action("tyhdPubs").get();
        route("/s/cats").to(JsonpController.class).action("tyhdCats").get();
        route("/s/auths").to(JsonpController.class).action("tyhdAuths").get();
        route("/s/tags").to(JsonpController.class).action("tyhdTags").get();
        route("/s/kts").to(JsonpController.class).action("tyhdKts").get();
        route("/s/kbs").to(JsonpController.class).action("tyhdKbs").get();

    }
}
