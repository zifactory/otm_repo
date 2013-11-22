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
 * Namespace app.modules.Group
 * Class MGroup.java
 * @date 11/1/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.user;

import app.models.Group;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zi.helper.ZHelper;

import java.util.Map;

public class MGroup {

    private final static Logger logger = LoggerFactory.getLogger(MGroup.class);

    public MGroup() {

    }

    /**
     * Insert Group Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names of attributes of this model, values
     * @return void
     */
    public static Group create(Map<String, Object> map) {
        Group grp = new Group();
        try {
            grp.fromMap(map);
            if (!grp.insert()) {
                ZHelper.setPesan("Ada Kesalahan diData Inputan!");
            } else ZHelper.setPesan("Data Berhasil.");
            logger.info(ZHelper.getPesan());
        } catch (DBException e) {
            logger.error(e.getMessage() + " Insert Ke modul DB gagal", e);
        }
        return grp;
    }

    /**
     * Read all records in Group
     *
     * @return LazyList<Group>
     */
    public static LazyList<Group> ReadAll() throws DBException {
        return Group.findAll();
    }

    /**
     * Read record in Group by ID
     *
     * @param ID Object
     * @return Group
     */
    public static Group ReadByID(Object ID) throws DBException {
        return Group.findById(ID);
    }

    /**
     * Update Group Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names of attributes of this model, values
     * @param ID  Long Generate ID ZHelperModel
     * @return true if record has ID
     */
    public static boolean update(Map<String, String> map, Long ID) throws DBException {
        boolean result = false;
        try {
            Group grp = Group.findById(ID);
            grp.fromMap(map);
            result = grp.saveIt();
            logger.info("Update Data id : " + ID.toString());
        } catch (DBException e) {
            logger.error(e.getMessage() + "Update Ke modul DB gagal", e);
        }
        return result;
    }

    public static boolean delete(Object ID) throws DBException {
        return Group.findById(ID).delete();
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
            LazyList<Group> mm = Group.where(subquery, params);
            for (Group mList : mm) {
                result = mList.delete();
            }
            ZHelper.setPesan("Data berhasil Dihapus");
            logger.info(ZHelper.getPesan());
            return result;
        } catch (DBException e) {
            logger.error(e.getMessage(), e);
            return result;
        }
    }
}
