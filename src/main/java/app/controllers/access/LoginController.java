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
 * Namespace app.controllers.access
 * Class LoginController.java
 * @date 11/1/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.access;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.GET;
import org.javalite.activeweb.annotations.POST;
import zi.helper.ZHelper;
import zi.helper.ZHelperAuth;

import java.util.Map;

public class LoginController extends AppController {

    @GET
    @POST
    public void index() {
        ZHelperAuth auth = (ZHelperAuth) session().get("authuser");
        if (auth != null) {
            redirect(context());
            return;
        }

        if (isPost()) {
            Map<String, String> map = params1st();
            view("say", map.get("nameoremail"));
        }
    }

    @POST
    public void signin() {

        String pass = ZHelper.simpleSaltedHash(param("nameoremail"), param("password"));
        UsernamePasswordToken token = new UsernamePasswordToken(param("nameoremail"), pass);
        token.setHost(remoteAddress());

        token.setRememberMe(Boolean.parseBoolean(param("rememberme")));
        try {
            // currentUser.login(token);
            ZHelperAuth auth = ZHelperAuth.getInstance();
            auth.AuthHelper(token);
            System.out.println(auth.isAuth());
            if (auth.isAuth()) {
                UsernamePasswordToken loginToken = (UsernamePasswordToken) auth.getAuthToken();
                ZHelper.logInfo(LoginController.class, loginToken.getHost() + " :: ==> getIpFrom Client ");
                session().put("authuser", auth);
                redirect(context());
                return;
            }
        } catch (UnknownAccountException uae) {
            ZHelper.logError(LoginController.class, uae.getMessage());
        } catch (IncorrectCredentialsException ice) {
            ZHelper.logError(LoginController.class, ice.getMessage());
        } catch (LockedAccountException lae) {
            ZHelper.logError(LoginController.class, lae.getMessage());
        }

        view("say", token.getUsername());
        render().noLayout();
    }

    @POST
    public void logout() {
        ZHelperAuth auth = (ZHelperAuth) session().get("authuser");
        if (auth == null) {
            redirect(context());
            return;
        }
        session().destroy();
        redirect(context());
        return;
    }
}
