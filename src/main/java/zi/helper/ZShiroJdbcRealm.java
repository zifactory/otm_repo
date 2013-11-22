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
 * Class ZShiroJdbcRealm.java
 * @date 11/13/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;


import app.models.User;
import app.modules.user.MUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.JdbcUtils;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZShiroJdbcRealm extends JdbcRealm {

    private static final Logger log = LoggerFactory.getLogger(ZShiroJdbcRealm.class);
    protected String jndiDataSourceName;

    public ZShiroJdbcRealm() {
        setName("jdbcRealm"); //This name must match the name in the User class's getPrincipals() method
        setCredentialsMatcher(new Sha256CredentialsMatcher());
    }

    public String getJndiDataSourceName() {
        return jndiDataSourceName;
    }

    public void setJndiDataSourceName(String jndiDataSourceName) {
        this.jndiDataSourceName = jndiDataSourceName;
        this.dataSource = getDataSourceFromJNDI(jndiDataSourceName);
    }

    private DataSource getDataSourceFromJNDI(String jndiDataSourceName) {
        try {
            InitialContext ic = new InitialContext();
            return (DataSource) ic.lookup(jndiDataSourceName);
        } catch (NamingException e) {
            log.error("JNDI error while retrieving " + jndiDataSourceName, e);
            throw new AuthorizationException(e);
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        String username = userPassToken.getUsername();

        if (username == null) {
            log.debug("Username is null.");
            return null;
        }

        // read password hash and salt from db
        User UserDB = MUser.ReadByName(username);

        if (UserDB.get("password") == null) {
            log.debug("No account found for user [" + username + "]");
            return null;
        }
        log.info(userPassToken.getCredentials() + " :::=> token" + getName());
        // return salted credentials
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(UserDB.get("name"), UserDB.get("password"), new SimpleByteSource("OTransmedia.2.0"), getName());

        log.info(info.getCredentials() + " ::: => salt");
        return info;
    }

    private PasswdSalt getPasswordForUser(String username) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(authenticationQuery);
            statement.setString(1, username);

            resultSet = statement.executeQuery();

            boolean hasAccount = resultSet.next();
            if (!hasAccount)
                return null;

            String salt = null;
            String password = resultSet.getString(1);

            salt = "OTransmedia.2.0";

            if (resultSet.getMetaData().getColumnCount() > 1)
                salt = "OTransmedia.2.0";//resultSet.getString(2);

            if (resultSet.next()) {
                throw new AuthenticationException("More than one user row found for user [" + username + "]. Usernames must be unique.");
            }

            return new PasswdSalt(password, salt);
        } catch (SQLException e) {
            final String message = "There was a SQL error while authenticating user [" + username + "]";
            if (log.isErrorEnabled()) {
                log.error(message, e);
            }
            throw new AuthenticationException(message, e);

        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(conn);
        }
    }

}

class PasswdSalt {

    public String password;
    public String salt;

    public PasswdSalt(String password, String salt) {
        super();
        this.password = password;
        this.salt = salt;
    }

}