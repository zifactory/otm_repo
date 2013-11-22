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
 * Namespace zi.helper
 * Class ZHelperAuth.java
 * @date 11/15/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import app.models.User;
import app.modules.user.MUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;
import java.util.Arrays;

public class ZHelperAuth implements Serializable {

    private static ZHelperAuth singleton;
    private boolean authenc = false;
    private User user = null;
    private char[] tokenLogin = null;
    private AuthenticationToken authToken = null;

    public ZHelperAuth() {
    }

    public static synchronized ZHelperAuth getInstance() {

        try {
            if (null == singleton) {
                singleton = new ZHelperAuth();
            }
        } catch (Exception ex) {
            ZHelper.logInfo(ZHelperAuth.class, ex.getMessage());
        }
        return singleton;
    }

    public static void logout() {

    }

    public void setisAuth(boolean auth) {
        authenc = auth;

    }

    public boolean isAuth() {
        return authenc;
    }

    public void AuthHelper(AuthenticationToken token) throws AuthenticationException {

        //identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        String username = userPassToken.getUsername();

        if (username == null) {
            ZHelper.logInfo(ZHelperAuth.class, "Username is null.");
            return;
        }

        // read password hash and salt from db
        User UserDB = MUser.ReadByName(username);

        if (UserDB.get("password") == null) {
            ZHelper.logInfo(ZHelperAuth.class, "No account found for user [" + username + "]");
            return;
        } else {
            setUser(UserDB);
            //tokenLogin {userpassword + ip}
            setTokenLogin(ZHelper.simpleSaltedHash(UserDB.get("email").toString(), UserDB.get("password").toString(), userPassToken.getHost()).toCharArray());
            ZHelper.logInfo(ZHelperAuth.class, "account found for user [" + username + "] in <" + userPassToken.getHost() + "> :: => [" + String.valueOf(getToken()) + "]");
        }

        UsernamePasswordToken info = new UsernamePasswordToken(UserDB.get("name").toString(),
                UserDB.get("password").toString(), userPassToken.isRememberMe(), userPassToken.getHost());
        // return salted credentials
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(UserDB.get("name"),
//                ZHelper.simpleSaltedHash(UserDB.get("name").toString(),
//                        userPassToken.getPassword().toString()), "jdbcRealm");

        // for compare secure auth password user
        if (Arrays.equals(info.getPassword(), userPassToken.getPassword())) {
            ZHelper.logInfo(ZHelperAuth.class, String.valueOf(userPassToken.getCredentials()) + " :::=> token" + UserDB.get("name"));
            setisAuth(true);
            ZHelper.logInfo(ZHelperAuth.class, info.getCredentials().toString() + " ::: => salt");
        } else setisAuth(false);

        setAuthToken(info);
    }

    public AuthenticationToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthenticationToken info) {
        this.authToken = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public char[] getToken() {
        return tokenLogin;
    }

    public String getTokenS() {
        return String.valueOf(getToken());
    }

    public void setTokenLogin(char[] token) {
        this.tokenLogin = token;
    }
}
