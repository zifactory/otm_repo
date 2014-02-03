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
 * Namespace app.test
 * Class UnmanagedID.java
 * @date 10/30/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.test;

import app.models.*;
import app.modules.main.MModule;
import org.javalite.activejdbc.LazyList;
import org.junit.Test;
import zi.helper.ZHelper;
import zi.helper.test.ActiveJDBCTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class UnmanagedID extends ActiveJDBCTest {
    @Test
    public void shouldInsertNewRecord() {

        Book book = new Book();
        book.set("isbn", "88868677");
        book.set("deskripsi", "desksatu");
        book.set("judul", "judulsatu");
        book.insert();

        Book book2 = new Book();
        book2.set("isbn", "888678900");
        book2.set("deskripsi", "deskdua");
        book2.set("judul", "juduldua");
        book2.insert();

        the(book.count()).shouldBeEqual(10);
        the(book.findById(book.getId()).get("isbn")).shouldBeEqual("88868677");
        the(book.findById(book2.getId()).get("isbn")).shouldBeEqual("888678900");
    }

    @Test
    public void insertCrossModule() {
        Map<String, Object> mm = new HashMap<String, Object>();
        mm.put("versi", "1.7");
        mm.put("is_enabled", 1);
        mm.put("nama", "judulsatu");

        Module mdl = MModule.create(mm);
        mm.clear();
        mm.put("versi", "1.7");
        mm.put("is_enabled", 1);
        mm.put("nama", "juduldua");
        Module mdl2 = MModule.create(mm);

        //a(MModule.delete(mdl.getId())).shouldBeTrue();
        a(MModule.delete("versi = ?", "1.0")).shouldBeFalse();
        the(MModule.ReadAll().size()).shouldBeEqual(2);
//        the(MModule.ReadByID(mdl2.getId()).get("versi")).shouldBeEqual("1.7");
//        the(MModule.ReadByID(mdl2.getId()).get("nama")).shouldBeEqual("juduldua");
    }

    @Test
    public void shouldInsertUserTest() {

        Group grp = new Group();
        grp.set("nama", "admin");
        grp.insert();

        User user = new User();
        user.set("grp_id", grp.getId());
        user.set("name", "surya");
        user.set("password", "e819d8d3516336ee93e7a6f9c61f592473d6ef10ec634ce306786368b5e1155d");
        user.insert();

        User user1 = new User();
        user1.set("grp_id", grp.getId());
        user1.set("name", "admin");
        user1.set("password", "e819d8d3516336ee93e7a6f9c61f592473d6ef10ec634ce306786368b5e1155d");
        user1.insert();

//        the(user.count()).shouldBeEqual(10);
//        the(user.findById(user.getId()).get("isbn")).shouldBeEqual("88868677");
//        the(user.findById(book2.getId()).get("isbn")).shouldBeEqual("888678900");
    }

    @Test
    public void wouldInsertGrpID() {
        Group grp = new Group();
        grp.set("nama", "member");
        grp.insert();

        Group grp1 = new Group();
        grp1.set("nama", "publisher");
        grp1.insert();
    }

    @Test
    public void updateUnmanagedID() {
        Category cat = Category.findById(Long.parseLong("1384487945844"));
        cat.set("keterangan", "Manajemen Sekolah 1234");
        cat.insert();

        a(cat.get("keterangan")).shouldEqual("Manajemen Sekolah 1234");
    }

    @Test
    public void whereMultiKey() {
        Vector<Object> obj = new Vector<Object>();
        obj.addElement("%66%");
        obj.addElement("%66%");
        obj.addElement("%66%");
        LazyList<Category> cats = Category.where("id like ? or keterangan like ? or root like ?", obj.toArray());
        ZHelper.logInfo(UnmanagedID.class, String.valueOf(cats.size()));
        for (Category cat : cats) {
            ZHelper.logInfo(UnmanagedID.class, " record" + cat.getId() + " | " + cat.get("keterangan"));
        }

    }

}
