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
 * Namespace app.modules.Member
 * Class MMember.java
 * @date 11/25/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.user;

import app.models.Member;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import zi.helper.ZHelper;

import java.util.Map;

public class MMember {

    public MMember() {
    }

    /**
     * Insert Member Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names of attributes of this model, values
     * @return void
     */
    public static Member create(Map map) {
        Member mbr = new Member();
        try {
            mbr.fromMap(map);
            if (!mbr.insert()) {
                ZHelper.setPesan("Ada Kesalahan diData Inputan!");
            } else ZHelper.setPesan("Data Berhasil.");
            ZHelper.logInfo(MMember.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logError(MMember.class, e.getMessage() + " Insert Ke modul DB gagal");
        }
        return mbr;
    }

    /**
     * Read all records in Member
     *
     * @return LazyList<Member>
     */
    public static LazyList<Member> ReadAll() throws DBException {
        return Member.findAll();
    }

    /**
     * Read record in Member by ID
     *
     * @param ID Object
     * @return Member
     */
    public static Member ReadByID(Object ID) throws DBException {
        return Member.findById(ID);
    }

    /**
     * Read record in Member by NAME
     *
     * @param NAME Object
     * @return Member
     */
    public static Member ReadByName(Object NAME) throws DBException {
        Member member = new Member();
        LazyList<Member> mbr = member.find("firstname = ? or lastname = ?", NAME);
        for (Member mList : mbr) {
            member = mList;
        }
        return member;
    }

    /**
     * Update Member Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names of attributes of this model, values
     * @param ID  Long Generate ID ZHelperModel
     * @return true if record has ID
     */
    public static boolean update(Map map, Long ID) throws DBException {
        boolean result = false;
        try {
            Member mbr = Member.findById(ID);
            mbr.fromMap(map);
            result = mbr.saveIt();
            ZHelper.logInfo(MMember.class, "Update Data id : " + ID.toString());
        } catch (DBException e) {
            ZHelper.logError(MMember.class, e.getMessage() + "Update Ke modul DB gagal");
        }
        return result;
    }

    public static boolean delete(Object ID) throws DBException {
        return Member.findById(ID).delete();
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
            LazyList<Member> mm = Member.where(subquery, params);
            for (Member mList : mm) {
                result = mList.delete();
            }
            ZHelper.setPesan("Data berhasil Dihapus");
            ZHelper.logError(MMember.class, ZHelper.getPesan());
            return result;
        } catch (DBException e) {
            ZHelper.logError(MMember.class, e.getMessage());
            return result;
        }
    }
}
