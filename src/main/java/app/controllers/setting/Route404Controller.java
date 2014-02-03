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
 * Namespace app.controllers.setting
 * Class Route404Controller.java
 * @date 12/31/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.setting;

import org.javalite.activeweb.AppController;

public class Route404Controller extends AppController {
    public void index() {
        render("/system/404");
    }
}