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
 * Namespace app.config
 * Class RouteConfig.java
 * @date 10/27/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.config;

import app.controllers.access.LoginController;
import app.controllers.accounts.ProfilesController;
import app.controllers.setting.CategoryController;
import org.javalite.activeweb.AbstractRouteConfig;
import org.javalite.activeweb.AppContext;

public class RouteConfig extends AbstractRouteConfig {
    public void init(AppContext appContext) {
        //route("/myposts").to(PostsController.class);
//        route("/{action}/buku/{id}").to(BooksController.class);
//        route("/{action}/{controller}/{id}").get();
//        route("/{action}/listbuku").to(BookContentsController.class).get();
        /* route unique untuk level login 0 -> user , 1 -> publisher , 3 -> administrator*/
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

    }
}
