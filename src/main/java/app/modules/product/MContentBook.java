/**
 * Copyright 2014 Nanang Suryadi || nanang.ask@gmail.com
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
 * Namespace app.modules.product Class MContentBook.java
 *
 * @date 1/5/14
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.product;

import app.models.Book;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import zi.helper.ZHelper;

import java.util.Map;

public class MContentBook {

    public MContentBook() {

    }

    /**
     * Insert Book Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     * of attributes of this model, values
     * @return void
     */
    public static Book create(Map map) {
        Book book = new Book();
        try {
            book.fromMap(map);
            if (!book.insert()) {
                ZHelper.setPesan("Ada Kesalahan diData Inputan!");
            } else {
                ZHelper.setPesan("Data Berhasil.");
            }
            ZHelper.logInfo(MContentBook.class, ZHelper.getPesan());
        } catch (DBException e) {
            ZHelper.logInfo(MContentBook.class, e.getMessage());
        }
        return book;
    }

    /**
     * Read all records in Book
     *
     * @return LazyList<Book>
     */
    public static LazyList<Book> ReadAll() throws DBException {
        return Book.findAll();
    }

    /**
     * Read record in Book by ID
     *
     * @param ID Object
     * @return Book
     */
    public static Book ReadByID(Object ID) throws DBException {
        return Book.findById(ID);
    }

    public static LazyList<Book> ReadByJudul(Object JUDUL) throws DBException {
        return Book.where("judul like ?", "%" + JUDUL + "%");
    }

    public static String getJudulByID(Object ID) throws DBException {
        return Book.findById(ID).getString("judul");
    }

    /**
     * Update Book Record with Map<String,String>
     *
     * @param map map with attributes to overwrite this models'. Keys are names
     * of attributes of this model, values
     * @param ID Long Generate ID ZHelperModel
     * @return true if record has ID
     */
    public static boolean update(Map map, Long ID) throws DBException {
        boolean result = false;
        try {
            Book book = Book.findById(ID);
            book.fromMap(map);
            result = book.saveIt();
            ZHelper.logInfo(MContentBook.class, "Update Data id : " + ID.toString());
        } catch (DBException e) {
            ZHelper.logInfo(MContentBook.class, e.getMessage() + "Update Ke modul DB gagal");
        }
        return result;
    }

    public static boolean delete(Object ID) throws DBException {
        return Book.findById(ID).delete();
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
            LazyList<Book> mm = Book.where(subquery, params);
            for (Book mList : mm) {
                result = mList.delete();
            }
            ZHelper.setPesan("Data berhasil Dihapus");
            ZHelper.logInfo(MContentBook.class, ZHelper.getPesan());
            return result;
        } catch (DBException e) {
            ZHelper.logInfo(MContentBook.class, e.getMessage());
            return result;
        }
    }
}
