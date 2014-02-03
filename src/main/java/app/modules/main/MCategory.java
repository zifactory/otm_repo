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
 * Namespace app.modules.main Class MCategory.java
 *
 * @date 12/10/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.main;

import app.models.Category;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import zi.helper.ZHelper;

import java.util.Map;

public class MCategory {

    public MCategory() {
    }

    /**
     * Insert Category Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     * of attributes of this model, values
     * @return void
     */
    public static Category create(Map map) {
        Category cat = new Category();
        try {
            cat.fromMap(map);
            if (!cat.insert()) {
                ZHelper.setPesan("Ada Kesalahan diData Inputan!");
            } else {
                ZHelper.setPesan("Data Berhasil.");
            }
            ZHelper.logInfo(MCategory.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logInfo(MCategory.class, e.getMessage());
        }
        return cat;
    }

    /**
     * Read all records in Category
     *
     * @return LazyList<Category>
     */
    public static LazyList<Category> ReadAll() throws DBException {
        return Category.findAll();
    }

    /**
     * Read record in Category by ID
     *
     * @param ID Object
     * @return Category
     */
    public static Category ReadByID(Object ID) throws DBException {
        return Category.findById(ID);
    }

    public static Category ReadByKeterangan(Object NAME) throws DBException {
        Category cats = null;
        LazyList<Category> cat = cats.find("keterangan = ?", NAME);
        for (Category mList : cat) {
            cats = mList;
        }
        return cats;
    }

    public static String getKetByID(Object ID) throws DBException {
        return Category.findById(ID).getString("keterangan");
    }

    /**
     * Update Category Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     * of attributes of this model, values
     * @param ID Long Generate ID ZHelperModel
     * @return true if record has ID
     */
    public static boolean update(Map map, Long ID) throws DBException {
        boolean result = false;
        try {
            Category cat = Category.findById(ID);
            cat.fromMap(map);
            result = cat.saveIt();
            ZHelper.logInfo(MCategory.class, "Update Data id : " + ID.toString());
        } catch (DBException e) {
            ZHelper.logInfo(MCategory.class, e.getMessage() + "Update Ke modul DB gagal");
        }
        return result;
    }

    public static boolean delete(Object ID) throws DBException {
        return Category.findById(ID).delete();
    }

    /**
     * @param subquery this is a set of conditions that normally follow the
     * "where" clause. Example: <code>"department = ? and dob > ?"</code>. If
     * this value is "*" and no parameters provided, then findAll is executed.
     * @param params list of parameters corresponding to the place holders in
     * the subquery.
     * @return boolean
     * @throws DBException
     */
    public static boolean delete(String subquery, Object params) {
        boolean result = false;
        try {
            LazyList<Category> mm = Category.where(subquery, params);
            for (Category mList : mm) {
                result = mList.delete();
            }
            ZHelper.setPesan("Data berhasil Dihapus");
            ZHelper.logInfo(MCategory.class, ZHelper.getPesan());
            return result;
        } catch (DBException e) {
            ZHelper.logInfo(MCategory.class, e.getMessage());
            return result;
        }
    }
}
