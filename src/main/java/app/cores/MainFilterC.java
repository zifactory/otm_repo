package app.cores;
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

import org.javalite.activeweb.controller_filters.HttpSupportFilter;
import org.javalite.common.Collections;

import java.util.Date;

/**
 * User: surya
 * Date: 10/8/13
 * Time: 9:43 AM
 */
public class MainFilterC extends HttpSupportFilter {

    @Override
    public void before() {
        Date timespam = new Date();
        long timesp = timespam.getTime();
        System.out.println("sebelum dilakukan MainFilterC :: " + timesp);
    }

    @Override
    public void after() {
        Date timespam = new Date();
        long timesp = timespam.getTime();
        System.out.println("setelah dilakukan MainFilterC :: " + timesp);
    }

    @Override
    public void onException(Exception e) {
        logError(e.toString(), e);
        render("/system/error", Collections.map("message", "403 asolole"));

    }
}
