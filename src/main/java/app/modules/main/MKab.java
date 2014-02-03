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
 * Namespace app.modules.main
 * Class MKab.java
 * @date 12/24/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.main;

import app.models.Kab;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import zi.helper.ZHelper;

import java.util.Map;

public class MKab {
    public MKab() {
    }

    /**
     * Insert Kab Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names of attributes of this model, values
     * @return void
     */
    public static Kab create(Map map) {
        Kab cat = new Kab();
        try {
            cat.fromMap(map);
            if (!cat.insert()) {
                ZHelper.setPesan("Ada Kesalahan diData Inputan!");
            } else ZHelper.setPesan("Data Berhasil.");
            ZHelper.logInfo(MKab.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logInfo(MKab.class, e.getMessage());
        }
        return cat;
    }

    /**
     * Read all records in Kab
     *
     * @return LazyList<Kab>
     */
    public static LazyList<Kab> ReadAll() throws DBException {
        return Kab.findAll();
    }

    /**
     * Read record in Kab by ID
     *
     * @param ID Object
     * @return Kab
     */
    public static Kab ReadByID(Object ID) throws DBException {
        return Kab.findById(ID);
    }

    public static Kab ReadByName(Object NAME) throws DBException {
        Kab cats = new Kab();
        LazyList<Kab> cat = cats.find("keterangan = ?", NAME);
        for (Kab mList : cat) {
            cats = mList;
        }
        return cats;
    }

    public static String getKetByID(Object ID) throws DBException {
        return Kab.findById(ID).getString("keterangan");
    }

    /**
     * Update Kab Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names of attributes of this model, values
     * @param ID  Long Generate ID ZHelperModel
     * @return true if record has ID
     */
    public static boolean update(Map map, Long ID) throws DBException {
        boolean result = false;
        try {
            Kab cat = Kab.findById(ID);
            cat.fromMap(map);
            result = cat.saveIt();
            ZHelper.logInfo(MKab.class, "Update Data id : " + ID.toString());
        } catch (DBException e) {
            ZHelper.logInfo(MKab.class, e.getMessage() + "Update Ke modul DB gagal");
        }
        return result;
    }

    public static boolean delete(Object ID) throws DBException {
        return Kab.findById(ID).delete();
    }

    /**
     * @param subquery this is a set of conditions that normally follow the "where" clause. Example:
     *                 <code>"department = ? and dob > ?"</code>. If this value is "*" and no parameters provided, then findAll is executed.
     * @param params   list of parameters corresponding to the place holders in the subquery.
     * @return boolean
     * @throws DBException
     */
    public static boolean delete(String subquery, Object params) {
        boolean result = false;
        try {
            LazyList<Kab> mm = Kab.where(subquery, params);
            for (Kab mList : mm) {
                result = mList.delete();
            }
            ZHelper.setPesan("Data berhasil Dihapus");
            ZHelper.logInfo(MKab.class, ZHelper.getPesan());
            return result;
        } catch (DBException e) {
            ZHelper.logInfo(MKab.class, e.getMessage());
            return result;
        }
    }
}
