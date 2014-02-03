/**
 * Copyright 2013 Nanang Suryadi || nanang.ask@gmail.com
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
 * Namespace zi.helper Class ZHelperModel.java
 *
 * @date 10/28/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZHelperModel {

    public <T extends Model> ZHelperModel(T model) {

    }

    public static long getGenerateID() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static Map<String, Object> kategoriMaps(String judulKategori, List<Map> content) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("category_name", judulKategori);
        maps.put("contents", content);
        return maps;
    }

    public static <T extends Model> String typeaheadJS(LazyList<T> lazy, String v, String t) {
        try {
            ArrayList<Object> aArray = new ArrayList<>();
            for (T tyjs : lazy) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("value", tyjs.get(v));
                map.put("tokens", tyjs.getString(t).split(" "));
                map.put("id", tyjs.getId());
                aArray.add(map);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(aArray);
        } catch (JsonProcessingException e) {
            ZHelper.logError(ZHelperModel.class, e.getMessage());
            return null;
        }
    }

    public static <T extends Model> String select2JS(LazyList<T> lazy, String v, String t) {
        try {
            ArrayList<Object> aArray = new ArrayList<>();
            for (T tyjs : lazy) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("text", tyjs.getString(v));
                //map.put("tokens", tyjs.getString(t).split(" "));
                map.put("id", tyjs.getString(v));
                aArray.add(map);
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> result = new HashMap<>();
            result.put("dataset", aArray);
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            ZHelper.logError(ZHelperModel.class, e.getMessage());
            return null;
        }
    }
}
