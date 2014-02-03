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
 * Namespace zi.helper.test
 * Class HashTokenTest.java
 * @date 11/12/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper.test;

import org.javalite.test.jspec.JSpecSupport;
import org.junit.Test;
import zi.helper.ZHelperAuth;

public class HashTokenTest extends JSpecSupport {
    @Test
    public void testToken() {
        ZHelperAuth.simpleHash("adminadmin");
        ZHelperAuth.simpleSaltedHash("surya", "adminadmin");
        ZHelperAuth.simpleSaltedHash("friendlyrepairman", "surya");
        ZHelperAuth.simpleSaltedHash("unfriendlyrepairman", "surya");
        ZHelperAuth.simpleSaltedHash("mathematician", "surya");
        ZHelperAuth.simpleSaltedHash("physicien", "surya");
        ZHelperAuth.simpleSaltedHash("productsales", "surya");
        ZHelperAuth.simpleSaltedHash("servicessales", "surya");
    }
}
