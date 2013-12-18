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
 * Namespace app.controllers.accounts
 * Class ProfilesController.java
 * @date 11/20/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.accounts;

import app.models.User;
import app.modules.user.MUser;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.GET;
import zi.helper.ZHelperAuth;

public class ProfilesController extends AppController {

    @GET
    public void index() {
        ZHelperAuth auth = (ZHelperAuth) session().get("authuser");
        String ID = param("ID");
        User user = MUser.ReadByID(param("ID"));

        if (auth != null) {
            if (!auth.getUser().getId().equals(ID)) {
                user = MUser.ReadByID(param("ID"));
            } else user = auth.getUser();
        }

        if (user != null) {
            logInfo(user.getString("email"));
            logInfo(param("ID"));
            view("getUser", user);
        } else {
            view("msgbox", "User Tidak ditemukan : " + param("ID") +
                    "<br /> Untuk Bergabung dengan OTransmedia silakan <a  href=\"" + context() +
                    "/access/login\" ><strong>disini</strong></a>");
            render("/home/index");
        }
    }

    public void info() {

    }
}
