/**
 * Copyright 2014 Nanang Suryadi || nanang.ask@gmail.com
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
 * Namespace app.modules.user
 * Class MAuthor.java
 * @date 2/1/14
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.user;

import app.models.Author;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import zi.helper.ZHelper;

import java.util.Map;

public class MAuthor {

    /**
     * Insert Author Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     *            of attributes of this model, values
     * @return void
     */
    public static Author manyInsert(Map map) {
        Author auths = new Author();
        try {
            auths.fromMap(map);
            ZHelper.logInfo(MAuthor.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logError(MAuthor.class, e.getMessage() + " Insert Ke modul DB gagal");
        }
        return auths;
    }

    /**
     * Insert Author Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     *            of attributes of this model, values
     * @return void
     */
    public static Author create(Map map) {
        Author auths = new Author();
        try {
            auths.fromMap(map);
            if (!auths.insert()) {
                ZHelper.setPesan("Ada Kesalahan diData Inputan!");
            } else {
                ZHelper.setPesan("Data Berhasil.");
            }
            ZHelper.logInfo(MAuthor.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logError(MAuthor.class, e.getMessage() + " Insert Ke modul DB gagal");
        }
        return auths;
    }

    /**
     * Read all records in Author
     *
     * @return LazyList<Author>
     */
    public static LazyList<Author> ReadAll() throws DBException {
        return Author.findAll();
    }

    /**
     * Read record in Author by ID
     *
     * @param ID Object
     * @return Author
     */
    public static Author ReadByID(Object ID) throws DBException {
        return Author.findById(ID);
    }

    /**
     * Read record in Author by NAME
     *
     * @param NAME Object
     * @return Author
     */
    public static Author ReadByName(Object NAME) throws DBException {
        Author author = null;
        LazyList<Author> auth = Author.find("nama = ?", NAME);
        for (Author mList : auth) {
            author = mList;
        }
        return author;
    }

    /**
     * Update Author Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     *            of attributes of this model, values
     * @param ID  Long Generate ID ZHelperModel
     * @return true if record has ID
     */
    public static boolean update(Map map, Long ID) throws DBException {
        boolean result = false;
        try {
            Author mbr = Author.findById(ID);
            mbr.fromMap(map);
            result = mbr.saveIt();
            ZHelper.logInfo(MAuthor.class, "Update Data id : " + ID.toString());
        } catch (DBException e) {
            ZHelper.logError(MAuthor.class, e.getMessage() + "Update Ke modul DB gagal");
        }
        return result;
    }

    public static boolean delete(Object ID) throws DBException {
        return Author.findById(ID).delete();
    }

    /**
     * @param subquery this is a set of conditions that normally follow the
     *                 "where" clause. Example: <code>"department = ? and dob > ?"</code>. If
     *                 this value is "*" and no parameters provided, then findAll is executed.
     * @param params   list of parameters corresponding to the place holders in
     *                 the subquery.
     * @return boolean
     * @throws DBException
     */
    public static boolean delete(String subquery, Object params) {
        boolean result = false;
        try {
            LazyList<Author> mm = Author.where(subquery, params);
            for (Author mList : mm) {
                result = mList.delete();
            }
            ZHelper.setPesan("Data berhasil Dihapus");
            ZHelper.logError(MAuthor.class, ZHelper.getPesan());
            return result;
        } catch (DBException e) {
            ZHelper.logError(MAuthor.class, e.getMessage());
            return result;
        }
    }
}
