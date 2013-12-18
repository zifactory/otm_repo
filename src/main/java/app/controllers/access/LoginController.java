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

import app.models.Group;
import app.models.Member;
import app.models.User;
import app.modules.user.MGroup;
import app.modules.user.MMember;
import app.modules.user.MUser;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.Configuration;
import org.javalite.activeweb.annotations.GET;
import org.javalite.activeweb.annotations.POST;
import org.javalite.common.Collections;
import zi.helper.ZHelper;
import zi.helper.ZHelperAuth;

import java.util.HashMap;
import java.util.Map;

public class LoginController extends AppController {

    @GET
    @POST
    public void index() {
        ZHelperAuth auth = (ZHelperAuth) session().get("authuser");
        if (auth != null) {
            redirect(context());
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
        } catch (Exception e) {
            if (Configuration.getEnv().equalsIgnoreCase("development")) {
                render("/system/error", Collections.map("e", e)).noLayout();
            }
        }
        view("msgbox", "Email Tidak ditemukan : " + param("nameoremail") +
                "<br /> Untuk Bergabung dengan OTransmedia silakan <a  href=\"" + context() +
                "/access/login\" ><strong>disini</strong></a>");
    }

    @POST
    public void signup() {
        StringBuilder pesan = new StringBuilder();
        try {
            if (isPost()) {
                Map<String, String> map = params1st();
                if (map.containsKey("signin")) {
                    signin();
                    logInfo("<<<signin lewat action signup>>>");
                } else {
                    if (map.containsKey("signup")) {
                        if (!map.get("passwordup").equalsIgnoreCase("")) {
                            // for user table
                            String pass = ZHelper.simpleSaltedHash(map.get("email"), map.get("passwordup"));

                            Group grp = MGroup.ReadByName("member");
                            String id = String.valueOf(grp.getId());
                            logInfo("<<<signin lewat action ID Grp>>> :: " + id + map.get("passwordup"));
                            map.put("password", pass);
                            map.put("grp_id", id);
                            User user = MUser.create(map);

                            logInfo("<<<signin lewat action signup " + pass + ">>>");

                            // for member
                            String[] name = map.get("name").split(" ", 2);
                            logInfo("<<<signin>>>" + name[0]);
                            Map<String, Object> HashMember = new HashMap<String, Object>();
                            HashMember.put("firstname", name[0]);
                            if (name.length > 1) HashMember.put("lastname", name[1]);
                            HashMember.put("user_id", user.getId());
                            Member mbr = MMember.create(HashMember);

                            pesan.append("Selamat Datang : ");
                            pesan.append(user.get("name"));
                            pesan.append("<br />");
                            pesan.append("Email Anda : ");
                            pesan.append(user.get("email"));
                            pesan.append("<br />");
                            pesan.append("Password Anda : ");
                            pesan.append(map.get("passwordup"));
                            pesan.append("<br /> ID Member");
                            pesan.append(mbr.getId());
                            logInfo("<<<" + pesan.toString() + ">>>");
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (Configuration.getEnv().equalsIgnoreCase("development")) {
                render("/system/error", Collections.map("e", e)).noLayout();
            }
        }
        view("msgbox", pesan.toString());
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
    }
}
