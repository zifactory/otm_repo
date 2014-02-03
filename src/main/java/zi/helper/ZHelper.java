/**
 * Copyright 2013 Nanang Suryadi || nanang.ask@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * Namespace zi.helper Class ZHelper.java
 *
 * @date 10/31/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import org.javalite.activeweb.FormItem;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ZHelper {

    private static String pesan;
    private static long startExecTime;

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

    /*public static String simpleHash(String password) {
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
     }*/
    public static void logInfo(Class cls, String msg) {
        LoggerFactory.getLogger(cls).info(msg);
    }

    public static void logError(Class cls, String msg) {
        LoggerFactory.getLogger(cls).error(msg);
    }

    public static boolean shouldBeNull(Object param) {
        return param == null;
    }

    public static String shouldBeEmpty(Object param) {
        return param == null || param.toString().isEmpty() ? "" : String.valueOf(param);
    }

    public static boolean Zimkdir(Object ID, StringBuilder sb) {
        File f = new File(sb.toString());
        if (!f.exists()) {
            if (f.mkdir()) {
                ZHelper.logInfo(ZHelper.class, sb.toString() + " Directory Path \"p\" is created");
            } else {
                ZHelper.logInfo(ZHelper.class, sb.toString() + " Directory Path \"p\" is not created");
            }
        }
        sb.append(ID);
        sb.append("/i");
        f = new File(sb.toString());
        if (!f.mkdirs()) {
            return false;
        }
        ZHelper.popStringBuilder(sb, "/i");
        ZHelper.logInfo(ZHelper.class, sb.toString());
        sb.append("/f");
        f = new File(sb.toString());
        if (!f.mkdirs()) {
            return false;
        }
        ZHelper.popStringBuilder(sb, "/f");
        sb.append("/v");
        f = new File(sb.toString());
        return f.mkdirs();

    }

    public static StringBuilder popStringBuilder(StringBuilder sb, String chars) {
        int fIndex = sb.indexOf(chars);
        return sb.delete(fIndex, sb.length());
    }

    // dibuat untuk folder setiap user signup ...
    public static boolean users_mkdir(Object ID, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append("/p/");
        return ZHelper.Zimkdir(ID, sb);
    }

    // dibuat untuk folder setiap content create ...
    public static boolean content_mkdir(Object ID, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append("/c/");
        return ZHelper.Zimkdir(ID, sb);
    }

    public static void uploadFile(byte[] inputstream, File file) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
//            int read;
//            byte[] bytes = new byte[1024];

            //while ((read = inputstream.read(bytes)) != -1) {
            outputStream.write(inputstream);
            // }
            outputStream.flush();
            outputStream.close();
        }
    }

    private static boolean contentTypeSelector(FormItem item, String selector) {
        String type = item.getContentType();
        return type != null && type.startsWith(selector);
    }

    public static boolean imageSelector(FormItem item) {
        String selector = "image/";
        return contentTypeSelector(item, selector);
    }

    public static boolean pdfSelector(FormItem item) {
        String selector = "application/pdf";
        return contentTypeSelector(item, selector);
    }

    public static boolean videoSelector(FormItem item) {
        String selector = "video/";
        return contentTypeSelector(item, selector);
    }

    public static void startExecTime() {
        startExecTime = 0;
        startExecTime = System.currentTimeMillis();
    }

    public static void endExecTime() {
        Long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time is " + formatter.format((end - startExecTime) / 1000d) + " seconds");
    }

    public static String lowerCase(Object item) {
        return item.toString().toLowerCase();
    }

    public static void rekursiFolder(StringBuilder path) throws IOException {
        System.out.println(path.toString());
        File pathFile = new File(path.toString());
        String s[] = pathFile.list();
        for (String pFile : s) {
            logInfo(ZHelper.class, pFile);
            path.append("/");
            path.append(pFile);
            logInfo(ZHelper.class, path.toString());
            File file = new File(path.toString());
            if (!file.isDirectory()) {
                if (!file.canWrite()) {
                    throw new IOException("Delete: write protected" + file.getAbsolutePath());
                }
                if (file.isFile()) {
                    if (!file.delete()) {
                        throw new IOException("Delete: deletion failed" + file.getAbsolutePath());
                    }
                    logInfo(ZHelper.class, file.getName() + " Was Deleted!!!");
                }
            } else {
                rekursiFolder(path);
            }
            popStringBuilder(path, "/" + pFile);
        }
        if (pathFile.isDirectory()) {
            if (!pathFile.delete()) throw new IOException("Delete: deletion failed" + pathFile.getAbsolutePath());
        }
    }

    public static boolean ziRemovedir(Object ID, StringBuilder path) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(path);
            sb.append(ID);
            rekursiFolder(sb);
            return true;
        } catch (IOException io) {
            logError(ZHelper.class, io.getMessage());
            return false;
        }
    }
}
