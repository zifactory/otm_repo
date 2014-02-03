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
 * Namespace app.modules.main
 * Class MTag.java
 * @date 2/2/14
 * @Tag Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.main;

import app.models.Tag;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import zi.helper.ZHelper;

import java.util.Map;

public class MTag {

    /**
     * Insert Tag Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     *            of attributes of this model, values
     * @return void
     */
    public static Tag manyInsert(Map map) {
        Tag tags = new Tag();
        try {
            tags.fromMap(map);
            ZHelper.logInfo(MTag.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logError(MTag.class, e.getMessage() + " Insert Ke modul DB gagal");
        }
        return tags;
    }

    /**
     * Insert Tag Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     *            of attributes of this model, values
     * @return void
     */
    public static Tag create(Map map) {
        Tag tags = new Tag();
        try {
            tags.fromMap(map);
            if (!tags.insert()) {
                ZHelper.setPesan("Ada Kesalahan diData Inputan!");
            } else {
                ZHelper.setPesan("Data Berhasil.");
            }
            ZHelper.logInfo(MTag.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logError(MTag.class, e.getMessage() + " Insert Ke modul DB gagal");
        }
        return tags;
    }

    /**
     * Read all records in Tag
     *
     * @return LazyList<Tag>
     */
    public static LazyList<Tag> ReadAll() throws DBException {
        return Tag.findAll();
    }

    /**
     * Read record in Tag by ID
     *
     * @param ID Object
     * @return Tag
     */
    public static Tag ReadByID(Object ID) throws DBException {
        return Tag.findById(ID);
    }

    /**
     * Read record in Tag by NAME
     *
     * @param NAME Object
     * @return Tag
     */
    public static Tag ReadByName(Object NAME) throws DBException {
        Tag tag = null;
        LazyList<Tag> tags = Tag.find("tag = ?", NAME);
        for (Tag mList : tags) {
            tag = mList;
        }
        return tag;
    }

    /**
     * Update Tag Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     *            of attributes of this model, values
     * @param ID  Long Generate ID ZHelperModel
     * @return true if record has ID
     */
    public static boolean update(Map map, Long ID) throws DBException {
        boolean result = false;
        try {
            Tag mbr = Tag.findById(ID);
            mbr.fromMap(map);
            result = mbr.saveIt();
            ZHelper.logInfo(MTag.class, "Update Data id : " + ID.toString());
        } catch (DBException e) {
            ZHelper.logError(MTag.class, e.getMessage() + "Update Ke modul DB gagal");
        }
        return result;
    }

    public static boolean delete(Object ID) throws DBException {
        return Tag.findById(ID).delete();
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
            LazyList<Tag> mm = Tag.where(subquery, params);
            for (Tag mList : mm) {
                result = mList.delete();
            }
            ZHelper.setPesan("Data berhasil Dihapus");
            ZHelper.logError(MTag.class, ZHelper.getPesan());
            return result;
        } catch (DBException e) {
            ZHelper.logError(MTag.class, e.getMessage());
            return result;
        }
    }
}
