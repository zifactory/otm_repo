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
 * Namespace app.test
 * Class ManyToManyTest.java
 * @date 1/9/14
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.test;

import app.models.Group;
import app.models.Module;
import org.javalite.activejdbc.LazyList;
import org.junit.Test;
import zi.helper.test.ActiveJDBCTest;

public class ManyToManyTest extends ActiveJDBCTest {

    @Test
    public void groupToModel() {
//        try {
//            Group grp = Group.findById(Long.parseLong("1384487945823"));
//            Module mdl = Module.findById(Long.parseLong("1389238477259"));
//            grp.addNoID(mdl);
//        } catch (DBException db) {
//            System.out.println(db.getMessage());
//            a(db.getMessage()).shouldContain("ConstraintViolation");
//        }
    }

    @Test
    public void modelToGroup() {
//        try {
//            Group grp = Group.findById(Long.parseLong("1385349065347"));
//            Module mdl = Module.findById(Long.parseLong("1389238477259"));
//            mdl.addNoID(grp);
//        } catch (DBException db) {
//            System.out.println(db.getMessage());
//            a(db.getMessage()).shouldContain("ConstraintViolation");
//        }
    }

    @Test
    public void selectModul() {
        Group grp = Group.findById(Long.parseLong("1384487945823"));
        LazyList<Module> mdl = grp.getAll(Module.class);
        the(mdl.get(0).getId()).shouldBeEqual(Long.parseLong("1389238477257"));
    }
}
