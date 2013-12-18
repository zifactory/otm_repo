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
 * Namespace app.modules.test
 * Class UserTest.java
 * @date 11/1/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.modules.test;

import app.models.Member;
import app.models.User;
import org.junit.Test;
import zi.helper.test.ActiveJDBCTest;

public class UserTest extends ActiveJDBCTest {
    @Test
    public void insertGenIDGroup() {
//        Map<String, Object> usr = new HashMap<String, Object>();
//        usr.put("name", "surya");
//        usr.put("password", "Suryakencana1234");
//        usr.put("email", "nanang.ask@gmail.com");
//        // usr.put("grp_id", "8856654525"); //foreign key grp_id harus ada di id tabel group
//        User musr = MUser.create(usr);

        //a(MUser.delete("name = ?", "surya")).shouldBeTrue();
        //the(MUser.ReadAll().size()).shouldBeEqual(6);
    }

    @Test
    public void readByName() {
      /*  User UserDB = MUser.ReadByName("nanang.ask@gmail.com");
        a(UserDB.get("password")).shouldNotBeNull();*/
    }

    @Test
    public void OneToManyTest() {
        Member mbr = new Member();
        mbr.set("firstname", "nanang");
        mbr.set("lastname", "suryadi");
//        mbr.insert();
        User user = new User();
        user.set("name", "nanang suryadi");
        user.set("password", "nanang suryadi");
        user.set("email", "nanang@suryadi.com");
        user.insert();
        user.addNoID(mbr);
        Member mmbr = Member.findById(Long.parseLong("1385449670349"));

        a(mmbr.parent(User.class)).shouldNotBeNull();

    }

}
