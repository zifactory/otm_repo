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
 * Namespace app.controllers
 * Class HomeController.java
 * @date 11/6/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers;

import org.javalite.activeweb.AppController;
import zi.helper.ZHelper;
import zi.helper.ZHelperModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController extends AppController {
    public void index() {
        String[] kategori = {"Kesehatan & Fitnes", "Matematika & Fisika", "Manajemen Sekolah"};
        String[] produk = {"camelot.png", "moderncombat.png", "robot.jpg",
                "tesla.png", "logo.png"};

        List<Map> maps = new ArrayList<Map>();
        for (String s : produk) {
            maps.add(toMap(s));
        }

        List<Map> mapsList = new ArrayList<Map>();
        for (String s : kategori) {
            mapsList.add(ZHelperModel.kategoriMaps(s, maps));
        }
        ZHelper.logInfo(HomeController.class, String.valueOf(session().get("authuser")));
        view("authuser",session().get("authuser"));
        view("kontent", mapsList);
        render();
    }

    private Map<String, Object> toMap(String imageurl) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", imageurl);
        map.put("judul", "Camelot234");
        return map;
    }
}
