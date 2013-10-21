package zi.helper;/*
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

import java.io.FileInputStream;
import java.util.Properties;

/**
 * User: surya
 * Date: 9/19/13
 * Time: 11:25 AM
 */
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
