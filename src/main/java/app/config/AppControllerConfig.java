package app.config;
/*
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
import app.controllers.HomeController;
import app.controllers.access.LoginController;
import app.cores.LoginFilter;
import app.cores.MainFilterC;
import org.javalite.activeweb.AbstractControllerConfig;
import org.javalite.activeweb.AppContext;
import org.javalite.activeweb.controller_filters.DBConnectionFilter;
import org.javalite.activeweb.controller_filters.TimingFilter;

/**
 * User: surya
 * Date: 10/8/13
 * Time: 10:25 AM
 */
public class AppControllerConfig extends AbstractControllerConfig {

    public void init(AppContext context) {
        addGlobalFilters(new TimingFilter(), new LoginFilter(), new MainFilterC());
        add(new DBConnectionFilter()).to(BooksController.class, LoginController.class);
        add(new DBConnectionFilter()).to(HomeController.class);
        add(new DBConnectionFilter()).to(BookContentsController.class);
    }
}