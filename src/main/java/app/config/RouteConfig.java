package app.config;/*
 * Copyright (C) 2013 surya || nanang.ask@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import app.controllers.BookContentsController;
import app.controllers.BooksController;
import org.javalite.activeweb.AbstractRouteConfig;
import org.javalite.activeweb.AppContext;

/**
 * User: surya
 * Date: 9/22/13
 * Time: 9:58 PM
 */
public class RouteConfig extends AbstractRouteConfig {
    public void init(AppContext appContext) {
        //route("/myposts").to(PostsController.class);
        route("/{action}/buku/{id}").to(BooksController.class);
        route("/{action}/{controller}/{id}").get();
        route("/{action}/listbuku").to(BookContentsController.class).get();

    }
}