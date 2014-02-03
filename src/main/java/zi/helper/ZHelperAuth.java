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

import app.models.Module;
import app.models.User;
import app.modules.user.MUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ZHelperAuth implements Serializable {

    private static ZHelperAuth singleton;
    private boolean authenc = false;
    private User user = null;
    private LazyList<Module> module = null;
    private char[] tokenLogin = null;
    private AuthenticationToken authToken = null;
    private Map authModuls = null;

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

    public static String simpleHash(String password) {
        Sha256Hash sha256Hash = new Sha256Hash(password);
        String result = sha256Hash.toHex();

        System.out.println("Simple hash: " + result);
        return result;
    }

    public static String simpleSaltedHash(String username, String password) {
        return simpleSaltedHash(username, password, "OTransmedia.2.0");
    }

    public static String simpleSaltedHash(String key1, String key2, String salt) {
        Sha256Hash sha256Hash = new Sha256Hash(key2, (new SimpleByteSource(salt + key1)).getBytes());
        String result = sha256Hash.toHex();

        ZHelper.logInfo(ZHelper.class, key1 + " simple salted hash: " + result);
        return result;
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
        try {
            // read password hash and salt from db
            User UserDB = MUser.ReadByName(username);

            if (UserDB.get("password") == null) {
                ZHelper.logInfo(ZHelperAuth.class, "No account found for user [" + username + "]");
                setisAuth(false);
                return;
            } else {
                //untuk koleksi module si user berdasarkan grp idnya
                ZHelper.logInfo(ZHelperAuth.class, String.valueOf(UserDB.getLong("grp_id")));
                LazyList<Module> lMdl = MUser.getModules(UserDB.getLong("grp_id"));
                Map<String, Object> aModuls = new HashMap<>();
                for (Module mdl : lMdl) {
                    aModuls.put(mdl.getString("key_mod"), mdl.getString("nama"));
                }
                setListModul(aModuls);
                setUser(UserDB);
                //tokenLogin {emailpassword + ip}
                setTokenLogin(simpleSaltedHash(UserDB.get("email").toString(), UserDB.get("password").toString(), userPassToken.getHost()).toCharArray());
                ZHelper.logInfo(ZHelperAuth.class, "account found for user [" + username + "] in <" + userPassToken.getHost() + "> :: => [" + String.valueOf(getToken()) + "]");
            }

            UsernamePasswordToken info = new UsernamePasswordToken(UserDB.get("name").toString(),
                    UserDB.get("password").toString(), userPassToken.isRememberMe(), userPassToken.getHost());

            // return salted credentials
            ZHelper.logInfo(ZHelperAuth.class, String.valueOf(userPassToken.getPassword()) + " :::=> token" + UserDB.get("name"));
            ZHelper.logInfo(ZHelperAuth.class, String.valueOf(info.getPassword()) + " :::=> token" + info.getUsername());

//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(UserDB.get("name"),
//                ZHelper.simpleSaltedHash(UserDB.get("name").toString(),
//                        userPassToken.getPassword().toString()), "jdbcRealm");
//            PasswordService svc = new DefaultPasswordService();

            // for compare secure auth password user
            if (Arrays.equals(info.getPassword(), userPassToken.getPassword())) {
                // if (svc.passwordsMatch(userPassToken.getPassword(), String.valueOf(info.getPassword()))) {
                ZHelper.logInfo(ZHelperAuth.class, String.valueOf(userPassToken.getCredentials()) + " :::=> token" + UserDB.get("name"));
                setisAuth(true);
                ZHelper.logInfo(ZHelperAuth.class, info.getCredentials().toString() + " ::: => salt");
            } else setisAuth(false);
            setAuthToken(info);
        } catch (DBException db) {
            ZHelper.logError(ZHelperAuth.class, db.getMessage());
        }
    }

    public Map getListModul() {
        return this.authModuls;
    }

    private void setListModul(Map aModuls) {
        this.authModuls = aModuls;
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

    public LazyList<Module> getModules() {
        return module;
    }

    public void setModule(LazyList<Module> module) {
        this.module = module;
    }
}
