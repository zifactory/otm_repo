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
 * Class ZHelperConfig.java
 * @date 10/28/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import java.io.FileInputStream;
import java.util.Properties;

public class ZHelperConfig {
    private static ZHelperConfig singleton;
    private static Properties props = new Properties();

    public ZHelperConfig() {
        System.err.println(System.getProperty("user.dir"));
    }

    public static synchronized ZHelperConfig getInstance() {
        try {
            if (null == singleton) {
                singleton = new ZHelperConfig();
            }
            singleton.setProperty();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return singleton;
    }

    /**
     * Reads the property key of this interface.
     *
     * @param key The key of the property to read.
     * @return The value of the property or null if not set
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    /**
     * Reads the property key of this interface. <br /> The defaultValue is
     * returned if the key is not defined in the properties.
     *
     * @param key          The key of the property to read.
     * @param defaultValue The defaultValue if key is not defined.
     * @return The value of the property or defaultValue if not set.
     */
    public static String getProperty(String key, String defaultValue) {
        String value = ZHelperConfig.props.getProperty(key, defaultValue);
        if ((value == null) || (value.length() == 0)) {
            value = defaultValue;
        }
        return value;
    }

    public void setProperty() {
        FileInputStream f;
        try {
            f = new FileInputStream(System.getProperty("user.dir") + "/conf/sys.conf");
            System.err.println(System.getProperty("user.dir") + "/conf/sys.conf");
            ZHelperConfig.props.load(f);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String getDbUser() {
        return ZHelperConfig.getProperty("db.username");
    }

    public String getDbPassword() {
        return ZHelperConfig.getProperty("db.password");
    }

    public String getDbUrl() {
        return ZHelperConfig.getProperty("db.url");
    }

    public String getDbDriver() {
        return ZHelperConfig.getProperty("db.driver");
    }
}
