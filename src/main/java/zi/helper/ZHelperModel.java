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
 * Namespace zi.helper
 * Class ZHelperModel.java
 * @date 10/28/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZHelperModel {

    public static long getGenerateID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static Map<String, Object> kategoriMaps(String judulKategori, List<Map> content) {
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("judulKategori", judulKategori);
        maps.put("content", content);
        return maps;
    }
}
