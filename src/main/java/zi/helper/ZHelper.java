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
 * Class ZHelper.java
 * @date 10/31/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZHelper {
    private final static Logger logger = LoggerFactory.getLogger(ZHelper.class);
    private static String pesan;

    /**
     * StringBuilder Pesan
     *
     * @return String
     */
    public static String getPesan() {
        return pesan;
    }

    /**
     * StringBuilder Pesan
     *
     * @param pesan String
     * @return void
     */
    public static void setPesan(String pesan) {
        ZHelper.pesan = pesan;
    }

    public static String simpleHash(String password) {
        Sha256Hash sha256Hash = new Sha256Hash(password);
        String result = sha256Hash.toHex();

        System.out.println("Simple hash: " + result);
        return result;
    }

    public static String simpleSaltedHash(String username, String password) {
        return ZHelper.simpleSaltedHash(username, password, "OTransmedia.2.0");
    }

    public static String simpleSaltedHash(String key1, String key2, String salt) {
        Sha256Hash sha256Hash = new Sha256Hash(key2, (new SimpleByteSource(salt + key1)).getBytes());
        String result = sha256Hash.toHex();

        ZHelper.logInfo(ZHelper.class, key1 + " simple salted hash: " + result);
        return result;
    }

    public static void logInfo(Class cls, String msg) {
        LoggerFactory.getLogger(cls).info(msg);
    }

    public static void logError(Class cls, String msg) {
        LoggerFactory.getLogger(cls).error(msg);
    }

    public static Object valsWasNull(Object param) {
        return !param.equals(null) ? param : null;
    }

    public static Object valsWasInt(Object param) {
        return !param.equals(null) ? param : 0;
    }

    public static boolean isWasNull(Object param) {
        return param.equals("") || String.valueOf(param).isEmpty() || param.equals(null);
    }

}
