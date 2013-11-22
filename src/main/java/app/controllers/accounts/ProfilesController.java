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
 * Namespace app.controllers.accounts
 * Class ProfilesController.java
 * @date 11/20/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.accounts;

import org.javalite.activeweb.AppController;

public class ProfilesController extends AppController {
    public void index() {
        logInfo(param("ID"));
        view("id", param("ID"));
        render("/home/index").noLayout();
    }
}