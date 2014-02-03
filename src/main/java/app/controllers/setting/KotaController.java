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
 * Namespace app.controllers.setting Class KotaController.java
 *
 * @date 12/24/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.setting;

import app.models.Kota;
import app.modules.main.MKota;
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
import zi.helper.ZHelperModel;

public class KotaController extends AppController {

    /**
     * //GET : /3/kt display a list of all Kota return void
     */
    public void index() {
        HashMap<String, Object> grid = new HashMap<>();
        //set fields sesuai urutan colnames
        Vector<Object> akt = new Vector<>();
        akt.removeAllElements();
        LazyList<Kota> kt = MKota.ReadAll();
        for (Kota cat : kt) {
            Vector<Object> ct = new Vector<>();
            ct.removeAllElements();
            ct.addElement(cat.getId());
            ct.addElement(cat.getString("keterangan"));

            akt.add(ct);
        }

        String[] colnames = {"No.Kota", "Name Kota "};
        grid.put("title", "Master Kota");
        grid.put("urlAdd", context() + "/3/kt/add");
        grid.put("urlEdit", context() + "/3/kt");
        grid.put("urlDelete", context() + "/3/kt");
        grid.put("urlPrint", context() + "/3/kt");
        grid.put("ajaxSource", context() + "/3/kt/jp");

        grid.put("print", null);
        grid.put("colnames", colnames);
//        grid.put("columns", akt);
        grid.put("tooltipEdit", "Edit Kota");
        grid.put("tooltipRemove", "Remove Kota");

        view("gridview", grid);
        render("/layouts/gridViewJson");
//        render("/layouts/gridview");
    }

    /**
     * //GET : /3/kt/add/ return an HTML form for creating a new Kota return
     * void
     */
    @GET
    public void a001() {
        HashMap<String, Object> formulir = new HashMap<>();

        formulir.put("pid", null);
        formulir.put("title", "Kota Form");
        formulir.put("method", "POST");
        formulir.put("action", context() + "/3/kt");

        formulir.put("catid", 0);
        view("formulir", formulir);
        render("/setting/kota/fkt");
    }

    /**
     * {POST} : /3/kt create a new Kota .save return void
     */
    @POST
    public void s003() {
        if (isPost()) {
            //render("/layouts/gridview");

            if (params().containsKey("save") && !param("keterangan").equalsIgnoreCase("")) {
                Kota cat;
                logInfo(param("pid"));
                //if (!ZHelper.shouldBeNull(param("pid")) && !param("pid").equals("0")) {
                if (!ZHelper.shouldBeNull(param("pid")) && !param("pid").equals("0")) {
                    //update
                    cat = MKota.ReadByID(param("pid"));
                    logInfo(param("pid") + " :: Proses update record");
                } else {
                    //new record
                    cat = new Kota();
                    logInfo(param("pid") + " :: Proses New Record");
                }

                cat.set("keterangan", ZHelper.shouldBeEmpty(param("keterangan")));

                cat.insert();
            }
        }
        index();
    }

    /**
     * {GET} : /3/kt/{ID} View Form Detail return void
     */
    public void v005() {
        try {
            HashMap<String, Object> formulir = new HashMap<>();
            //Find Record By ID not found record null
            Kota ct = MKota.ReadByID(param("ID"));

            if (ct != null) {

                formulir.put("pid", ct.getId());
                formulir.put("fkt", ct);
                formulir.put("title", "Kota Form");
                formulir.put("method", "POST");
                formulir.put("action", context() + "/3/kt");
                view("formulir", formulir);
                render("/setting/kota/fkt");
            } else {
                view("msgbox", "Record Tidak ditemukan : " + param("ID"));
                index();
            }
        } catch (Exception ex) {
            render("/system/error", Collections.map("e", ex, "message", ex.getMessage())).layout("system_error");
        }
    }

    /**
     * {PUT} : /3/kt/{ID} update a specific Kota return void
     */
    @PUT
    public void u009() {

    }

    /**
     * Ajax with msgBox {DELETE} : /3/kt/{ID} delete a specific Kota return void
     */
    @DELETE
    public void d011() {
        // kirim msgBox confirm Record ID delete
        String msgbox;
        try {
            if (MKota.delete(param("ID"))) {
                msgbox = "Record Terhapus : " + param("ID") + "!!!";
            } else {
                msgbox = "Ada Kesalahan Pada Server Data Tidak Dapat Terhapus";
            }
        } catch (Exception db) {
            logInfo("log log log");
            msgbox = db.getMessage();
            logInfo(msgbox);
            render().noLayout();
        }
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
        String[] aColumns = {"id", "keterangan"};
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
                    sOrder += "`" + aColumns[Integer.parseInt(param("iSortCol_" + i))] + "` "
                            + (param("sSortDir_" + i).equalsIgnoreCase("asc") ? "asc" : "desc");
                }
            }
        }
        String sWhere = "";
        Vector<Object> sParams = new Vector<>();
        logInfo(String.valueOf(param("sSearch").equals("")));
        if (params().containsKey("sSearch") && !param("sSearch").equals("")) {
            for (int i = 0; i < aColumns.length; i++) {
                if (params().containsKey("bSearchable_" + i) && param("bSearchable_" + i).equalsIgnoreCase("true")) {
                    if (i == aColumns.length - 1) {
                        sWhere += aColumns[i] + " LIKE ? ";
                    } else {
                        sWhere += aColumns[i] + " LIKE ?  OR ";
                    }
                    String str = param("sSearch").equals("") ? "" : param("sSearch");
                    sParams.add("%" + str + "%");
                }
            }
        }
        LazyList<Kota> rResult = Kota.where(sWhere, sParams.toArray())
                .offset(sStart)
                .limit(sLimit)
                .orderBy(sOrder);

        Long iTotal = Kota.count();
        int iFilteredTotal = params().containsKey("sSearch") && !param("sSearch").equals("") ? rResult.size() : iTotal.intValue();
        /*
         * Data aaData Vector
         * */
        Vector<Object> aaData = new Vector<>();
        for (Kota cat : rResult) {
            Vector<Object> ct = new Vector<>();
            ct.removeAllElements();
            for (String sColumn : aColumns) {
                ct.addElement(cat.get(sColumn));
            }
            //column tambahan untuk edit dan delete
            String[] colAdj = {
                "<a href=\"" + context() + "/3/kt/" + ct.get(0) + "\" class=\"fa fa-file-text\" "
                + "data-toggle=\"tooltip\" title=\"Edit Action\"></a>",
                "<a href=\"" + context() + "/3/kt/" + ct.get(0) + "\" data-method=\"delete\" data-linkto=\"aw\"><i class=\"fa fa-trash-o\"></i></a>"
            };
            ct.addElement(colAdj[0]);
            ct.addElement(colAdj[1]);
            aaData.addElement(ct);
        }
        HashMap<String, Object> output = new HashMap<>();
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

    /**
     * Ajax untuk typeheadJS All Table Kota GET /1/c/kts
     */
    public void tyhdKats() {
        LazyList<Kota> kats = Kota.findAll();
        respond(ZHelperModel.typeaheadJS(kats, "keterangan", "keterangan")).contentType("text/json").status(200);
    }
}
