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
 * Namespace app.models
 * Class Tag.java
 * @date 2/2/14
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.models;

import app.cores.IModel;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsToPolymorphic;
import zi.helper.ZHelperModel;

@BelongsToPolymorphic(
        parents = {Video.class, Book.class},
        typeLabels = {"video", "buku"})
public class Tag extends Model implements IModel {
    @Override
    public boolean insert() {
        if (getId() == null) {
            setId(ZHelperModel.getGenerateID());
            return super.insert();
        } else {
            return super.save();
        }
    }
}
