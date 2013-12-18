/**
 * Copyright 2013 Nanang Suryadi || nanang.ask@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"){}
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
 * Class CategoryController.java
 * @date 12/3/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.setting;

import app.models.Category;
import app.modules.main.MCategory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.GET;
import org.javalite.activeweb.annotations.POST;
import org.javalite.activeweb.annotations.PUT;
import org.javalite.common.Collections;
import zi.helper.ZHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class CategoryController extends AppController {

     /* ===============================================================================
        GET     /3/cats                index            display a list of all category
        GET     /3/cats/add            a001             return an HTML form for creating a new category
        POST    /3/cats                s003             create a new category .save
        GET     /3/cats/id             v005             display a specific category
        - GET     /3/cats/id/edit        f007             return an HTML form for editing a category
        PUT     /3/cats/id             u009             update a specific category
        DELETE  /3/cats/id             d011             delete a specific category
        ==================================================================================*/

    /**
     * //GET : /3/cats
     * display a list of all category
     * return void
     */
    public void index() {
        HashMap<String, Object> grid = new HashMap<String, Object>();
        //set fields sesuai urutan colnames
        Vector<Object> acats = new Vector<Object>();
        acats.removeAllElements();
        LazyList<Category> cats = Category.findAll();
        for (Category cat : cats) {
            Vector<Object> ct = new Vector<Object>();
            ct.removeAllElements();
            ct.addElement(cat.getId());
            ct.addElement(cat.getString("keterangan"));
            Object str;
            if (cat.get("root") != null) {
                str = MCategory.getKetByID(cat.getLong("root") + " | " + String.valueOf(cat.get("root")));
            } else {
                str = cat.get("root");
            }
            ct.addElement(str);
            acats.add(ct);
        }

        String[] colnames = {"No.Category", "Category Name", "Root"};
        grid.put("title", "Master Category");
        grid.put("urlAdd", context() + "/3/cats/add");
        grid.put("urlEdit", context() + "/3/cats");
        grid.put("urlDelete", context() + "/3/cats");
        grid.put("urlPrint", context() + "/3/cats");
        grid.put("ajaxSource", context() + "/3/cats/jp");

        grid.put("print", null);
        grid.put("colnames", colnames);
//        grid.put("columns", acats);
        grid.put("tooltipEdit", "Edit Category");
        grid.put("tooltipRemove", "Remove Category");

        view("gridview", grid);
        render("/layouts/gridViewJson");
//        render("/layouts/gridview");
    }

    /**
     * //GET : /3/cats/add/
     * return an HTML form for creating a new category
     * return void
     */
    @GET
    public void a001() {
        HashMap<String, Object> formulir = new HashMap<String, Object>();
        LazyList<Category> cats = MCategory.ReadAll();
        formulir.put("pid", null);
        formulir.put("title", "Category Form");
        formulir.put("method", "POST");
        formulir.put("action", context() + "/3/cats");
        formulir.put("cats", cats);
        formulir.put("catid", 0);
        view("formulir", formulir);
        render("/setting/category/fcats");
    }

    /**
     * {POST} : /3/cats
     * create a new category .save
     * return void
     */
    @POST
    public void s003() {
        if (isPost()) {
            //render("/layouts/gridview");

            if (params().containsKey("save") && !param("keterangan").equalsIgnoreCase("")) {
                Category cat;
                logInfo(param("pid"));
                //if (!ZHelper.isWasNull(param("pid")) && !param("pid").equals("0")) {
                if (!ZHelper.isWasNull(param("pid")) && !param("pid").equals("0")) {
                    //update
                    cat = MCategory.ReadByID(param("pid"));
                    logInfo(param("pid") + " :: Proses update record");
                } else {
                    //new record
                    cat = new Category();
                    logInfo(param("pid") + " :: Proses New Record");
                }

                cat.set("keterangan", ZHelper.valsWasNull(param("keterangan")));
                if (!ZHelper.isWasNull(param("root")) && !param("root").isEmpty()) {
                    cat.set("root", param("root"));
                }
                cat.insert();
            }
        }
        index();
    }

    /**
     * {GET} : /3/cats/{ID}
     * View Form Detail
     * return void
     */
    public void v005() {
        try {
            HashMap<String, Object> formulir = new HashMap<String, Object>();
            //Find Record By ID not found record null
            Category ct = MCategory.ReadByID(param("ID"));

            if (ct != null) {
                LazyList<Category> cats = MCategory.ReadAll();
                formulir.put("pid", ct.getId());
                formulir.put("fcats", ct);
                formulir.put("title", "Category Form");
                formulir.put("method", "POST");
                formulir.put("action", context() + "/3/cats");
                formulir.put("cats", cats);
                formulir.put("catid", ct.get("root"));
                view("formulir", formulir);
                render("/setting/category/fcats");
            } else {
                view("msgbox", "Record Tidak ditemukan : " + param("ID") +
                        "<br /> Untuk Bergabung dengan OTransmedia silakan <a  href=\"" + context() +
                        "/access/login\" ><strong>disini</strong></a>");
                index();
            }
        } catch (Exception ex) {
            render("/system/error", Collections.map("e", ex, "message", ex.getMessage())).layout("system_error");
        }
    }

    /**
     * {PUT} : /3/cats/{ID}
     * update a specific category
     * return void
     */
    @PUT
    public void u009() {

    }

    /**
     * Ajax with msgBox
     * {DELETE} : /3/cats/{ID}
     * delete a specific category
     * return void
     */
    @DELETE
    public void d011() {
        // kirim msgBox confirm Record ID delete
        String msgbox;
        if (MCategory.delete(param("ID"))) {
            msgbox = "Record Terhapus : " + param("ID") + "!!!";
        } else msgbox = "Ada Kesalahan Pada Server Data Tidak Dapat Terhapus";

        respond(msgbox).contentType("text/plain").status(200);
    }

    /*
    *  aColumns == field database
    *  Columns == field table
    *  Model == Bean Table database
    *  Params == HTTP Respond
    *  Parent == AppController<this>
    * */
    public void dtbl() {
        /*
        *  column field from database
        * */
        String[] aColumns = {"id", "keterangan", "root"};
        //Paging
        int sLimit = 0;
        int sStart = 0;
        if (params().containsKey("iDisplayStart") && !param("iDisplayLength").equalsIgnoreCase("-1")) {
            sLimit = Integer.parseInt(param("iDisplayLength"));
            sStart = Integer.parseInt(param("iDisplayStart"));
        }
        String sOrder = "";
        if (params().containsKey("iSortCol_0")) {
            for (int i = 0; i < Integer.parseInt(param("iSortingCols")); i++) {

                if (param("bSortable_" + param("iSortCol_" + i)).equalsIgnoreCase("true")) {
                    sOrder += "`" + aColumns[Integer.parseInt(param("iSortCol_" + i))] + "` " +
                            (param("sSortDir_" + i).equalsIgnoreCase("asc") ? "asc" : "desc");
                }
            }
        }
        String sWhere = "";
        Vector<Object> sParams = new Vector<Object>();
        logInfo(String.valueOf(param("sSearch").equals("")));
        if (params().containsKey("sSearch") && !param("sSearch").equals("")) {
            for (int i = 0; i < aColumns.length; i++) {
                if (params().containsKey("bSearchable_" + i) && param("bSearchable_" + i).equalsIgnoreCase("true")) {
                    if (i == aColumns.length - 1) {
                        sWhere += aColumns[i] + " LIKE ? ";
                    } else sWhere += aColumns[i] + " LIKE ?  OR ";
                    String str = param("sSearch").equals("") ? "" : param("sSearch");
                    sParams.add("%" + str + "%");
                }
            }
        }
        LazyList<Category> rResult = Category.where(sWhere, sParams.toArray())
                .offset(sStart)
                .limit(sLimit)
                .orderBy(sOrder);

        Long iTotal = Category.count();
        int iFilteredTotal = params().containsKey("sSearch") && !param("sSearch").equals("") ? rResult.size() : iTotal.intValue();
        /*
        * Data aaData Vector
        * */
        Vector<Object> aaData = new Vector<Object>();
        for (Category cat : rResult) {
            Vector<Object> ct = new Vector<Object>();
            ct.removeAllElements();
            for (String sColumn : aColumns) {
                ct.addElement(cat.get(sColumn));
            }
            //column tambahan untuk edit dan delete
            String[] colAdj = {
                    "<a href=\"" + context() + "/3/cats/" + ct.get(0) + "\" class=\"fa fa-file-text\" " +
                            "data-toggle=\"tooltip\" title=\"Edit Action\"></a>",
                    "<a href=\"" + context() + "/3/cats/" + ct.get(0) + "\" data-method=\"delete\" data-linkto=\"aw\"><i class=\"fa fa-trash-o\"></i></a>"
            };
            ct.addElement(colAdj[0]);
            ct.addElement(colAdj[1]);
            aaData.addElement(ct);
        }
        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("sEcho", param("sEcho"));
        output.put("iTotalRecords", iTotal);
        output.put("iTotalDisplayRecords", iFilteredTotal);
        output.put("aaData", aaData.toArray());
        ObjectMapper mapper = new ObjectMapper();

        try {
            String hasil = param("callback") + "(" + mapper.writeValueAsString(output) + ");";
            respond(hasil).contentType("text/json").status(200);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
