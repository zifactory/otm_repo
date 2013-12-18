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
 * Namespace app.cores
 * Class LoginFilter.java
 * @date 11/11/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.cores;

import org.javalite.activeweb.controller_filters.HttpSupportFilter;
import zi.helper.ZHelper;
import zi.helper.ZHelperAuth;

public class LoginFilter extends HttpSupportFilter {

    public void before() {
        ZHelper.logInfo(LoginFilter.class, remoteAddress());
        ZHelper.logInfo(LoginFilter.class, "ini buat object user module");

        ZHelperAuth auth = (ZHelperAuth) session().get("authuser");

        if (auth == null) {
            redirect(context() + "/access/login");
            ZHelper.logInfo(LoginFilter.class, "Auth false mengarah ke signup form");
        } else {
            if (auth.isAuth()) {
                view("authuser", auth);
                ZHelper.logInfo(LoginFilter.class, " ::>>> ini buat :: " + auth.getUser().get("password"));
            }
        }
    }

    public void after() {

    }
}
