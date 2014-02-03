/**
 * Copyright 2014 Nanang Suryadi || nanang.ask@gmail.com
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
 * Namespace app.controllers.contents Class BooksController.java
 *
 * @date 1/16/14
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package app.controllers.contents;

import app.models.*;
import app.modules.main.MCategory;
import app.modules.product.MContentBook;
import app.modules.user.MAuthor;
import app.modules.user.MPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.FormItem;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;
import org.javalite.common.Collections;
import org.javalite.common.Util;
import zi.helper.ZHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksController extends AppController {
    /**
     * //GET : /1/c/book display a list of all category return void
     */
    public void index() {
        HashMap<String, Object> grid = new HashMap<>();
        //set fields sesuai urutan colnames

        String[] colnames = {"No.Book", "Cover", "No.ISBN", "Title", "File"};
        grid.put("title", "List Buku Kontent");
        grid.put("urlAdd", context() + "/1/c/book/add");
        grid.put("urlEdit", context() + "/1/c/{ID}/book");
        grid.put("urlDelete", context() + "/1/c/{ID}/book");
        grid.put("urlPrint", context() + "/1/c/print/{ID}/book");
        grid.put("ajaxSource", context() + "/1/c/book/jp");

        grid.put("print", null);
        grid.put("colnames", colnames);
        grid.put("tooltipEdit", "Edit Buku");
        grid.put("tooltipRemove", "Remove Buku");

        view("gridview", grid);
        render("/layouts/gridViewJson");
    }

    public void a001() {
        HashMap<String, Object> formulir = new HashMap<>();
        formulir.put("pid", null);
        formulir.put("title", "Buku Form");
        formulir.put("method", "POST");
        formulir.put("action", context() + "/1/c/book");
        // formulir.put("cats", cats);
        //formulir.put("catid", 0);
        view("formulir", formulir);
    }

    @POST
    public void s003() {
        StringBuilder sbmsgbox = new StringBuilder();
        if (isPost()) {
            ZHelper.startExecTime();
            Iterator<FormItem> iterator = uploadedFiles();
            // collect enctype multi form data to Map
            HashMap<String, Object> formParams = new HashMap<>();
            Map<String, byte[]> aListIO = new HashMap<>();
            while (iterator.hasNext()) {
                try {
                    FormItem item = iterator.next();
                    if (item.isFile()) {
                        formParams.put(item.getFieldName(), item);
                        aListIO.put(item.getFieldName(), Util.bytes(item.getInputStream()));
                    } else {
                        formParams.put(item.getFieldName(), Util.read(item.getInputStream()));
                        logInfo(item.getFieldName() + " || Field Name");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (formParams.containsKey("save")) {
                if (!formParams.get("isbn").equals("")) {
                    try {
                        //simpan dulu didatabase baru upload file
                        Book book;
                        logInfo(param("pid"));
                        //if (!ZHelper.shouldBeNull(param("pid")) && !param("pid").equals("0")) {
                        if (!ZHelper.shouldBeNull(formParams.get("pid")) && !formParams.get("pid").equals("0")) {
                            //update
                            book = MContentBook.ReadByID(formParams.get("pid"));
                            logInfo(param("pid") + " :: Proses update record");
                        } else {
                            //new record
                            book = new Book();
                            logInfo(formParams.get("pid") + " :: Proses New Record");
                        }

                        if (!ZHelper.shouldBeNull(formParams.get("isbn")) && !formParams.get("isbn").toString().isEmpty()) {
                            book.set("judul", ZHelper.shouldBeEmpty(formParams.get("judul")));
                            book.set("isbn", formParams.get("isbn"));
                            book.set("deskripsi", formParams.get("deskripsi"));
                            book.set("sinopsis", formParams.get("sinopsis"));
                            logInfo("insert proses");
                            //cek list publisher terdaftar
                            String findKey = ZHelper.lowerCase(formParams.get("penerbit_nama"));
                            logInfo(findKey);
                            Publisher pbs = MPublisher.ReadByName(findKey);

                            if (null == pbs || !pbs.exists()) {
                                Map<String, Object> hashPbs = new HashMap<>();
                                hashPbs.put("nama", formParams.get("penerbit_nama"));
                                pbs = MPublisher.create(hashPbs);
                                logInfo("publisher null");
                            }
                            book.set("penerbit_id", pbs.getId());
                            //cel list kategori terdaftars
                            findKey = ZHelper.lowerCase(formParams.get("kategori_nama"));
                            Category cats = MCategory.ReadByKeterangan(findKey);

                            if (null == cats || !cats.exists()) {
                                Map<String, Object> hashCats = new HashMap<>();
                                hashCats.put("keterangan", formParams.get("kategori_nama"));
                                cats = MCategory.create(hashCats);
                                logInfo("category null");
                            }
                            book.set("kategori_id", cats.getId());
                        }

                        if (!book.insert()) {
                            sbmsgbox.append("Data tidak berhasil disimpan");
                        } else {
                            if (formParams.get("pengarang_nama") != null) {
                                //cek list pengarang terdaftar ManyToMany
                                Author auths;
                                String[] aString = ZHelper.lowerCase(formParams.get("pengarang_nama")).split(",");
                                LazyList<Author> fauth = book.getAll(Author.class);
                                for (Author ath : fauth) {
                                    book.remove(ath);
                                }
                                for (String find : aString) {
                                    auths = MAuthor.ReadByName(find);
                                    if (null == auths || !auths.exists()) {
                                        HashMap<String, Object> hashCats = new HashMap<>();
                                        hashCats.put("nama", find);
                                        auths = MAuthor.create(hashCats);
                                        logInfo("pengarang null");
                                    }
                                    try {
                                        book.addNoID(auths);
                                    } catch (DBException db) {
                                        if (!db.getMessage().contains("ConstraintViolation")) {
                                            logInfo(db.getMessage());
                                        }
                                    }
                                }
                            }
                            if (formParams.get("tag_nama") != null) {
                                //cek list tags buku polymorphic
                                Tag tag;
                                String[] aString = ZHelper.lowerCase(formParams.get("tag_nama")).split(",");
                                LazyList<Tag> ftag = book.getAll(Tag.class);
                                for (Tag tg : ftag) {
                                    book.remove(tg);
                                }
                                for (String find : aString) {
                                    tag = Tag.create("tag", find);
                                    logInfo("Tag null");
                                    try {
                                        book.addNoID(tag);
                                    } catch (DBException db) {
                                        if (!db.getMessage().contains("ConstraintViolation")) {
                                            logInfo(db.getMessage());
                                        }
                                    }
                                }
                            }
                            ZHelper.content_mkdir(book.getId(), getRealPath(""));
                            File oFile = null;
                            //untuk cover image kontent
                            FormItem item = (FormItem) formParams.get("image_url");
                            String urlImg = "/c/" + book.getId() + "/i/" + item.getFileName();
                            if (item.getFieldName().equalsIgnoreCase("image_url") && ZHelper.imageSelector(item) && 0 < aListIO.get("image_url").length) {
                                oFile = new File(getRealPath("") + "/c/" + book.getId() + "/i/" + item.getFileName());
                                // write the inputStream to a FileOutputStream
                                ZHelper.uploadFile((byte[]) aListIO.get("image_url"), oFile);
                                logInfo("Upload Image Cover Kontent berhasil");
                                book.set("image_url", urlImg);
                            }
                            // untuk url kontent statis
                            item = (FormItem) formParams.get("lokasi");
                            String urlLokasi = "/c/" + book.getId() + "/f/" + book.getId() + ".pdf";
                            if (item.getFieldName().equalsIgnoreCase("lokasi") && ZHelper.pdfSelector(item) && 0 < aListIO.get("lokasi").length) {
                                oFile = new File(getRealPath("") + "/c/" + book.getId() + "/f/" + book.getId() + ".pdf");
                                // write the inputStream to a FileOutputStream
                                ZHelper.uploadFile((byte[]) aListIO.get("lokasi"), oFile);
                                logInfo("Upload File Kontent berhasil");
                                book.set("lokasi", urlLokasi);
                                sbmsgbox.append("Record buku sudah tersimpan dengan file : ").append(item.getFileName());
                            } else {
                                sbmsgbox.append("Tidak ada File yang diupload")
                                        .append(" atau ")
                                        .append("Format Buku yang anda Upload salah [Pdf] >>> ")
                                        .append(item.getFileName())
                                        .append("Dengan No.Record : ")
                                        .append(book.getId());
                            }
                            if (book.save())
                                sbmsgbox.append('\n')
                                        .append("Record Tersimpan")
                                        .append("Dengan No.Record : ")
                                        .append(book.getId());
                        }
                    } catch (IOException | DBException ex) {
                        sbmsgbox.append('\n')
                                .append("File tidak berhasil diupload coba ulangi dengan data yang sama");
                        Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    sbmsgbox.append("No.ISBN tidak boleh kosong");
                }
            }
        }
        ZHelper.endExecTime();
        if (0 < sbmsgbox.length()) {
            flash("msgbox", sbmsgbox.toString());
        }
        redirect(context() + "/1/c/book");
    }

    /**
     * {GET} : /1/c/{ID}/book View Form Detail return void
     */
    public void v005() {
        try {
            HashMap<String, Object> formulir = new HashMap<>();
            //Find Record By ID not found record null
            Book ct = MContentBook.ReadByID(param("ID"));

            if (ct != null) {
                Publisher pbs = ct.parent(Publisher.class);
                Category cat = ct.parent(Category.class);
                String fauth = "";
                LazyList<Author> auths = ct.getAll(Author.class);
                if (0 < auths.size() && !auths.isEmpty()) {
                    List<String> list = new ArrayList<>();
                    for (Author auth : auths) {
                        list.add(auth.getString("nama"));
                    }
                    fauth = Util.join(list, ",");
                }
                String ftags = "";
                LazyList<Tag> tags = ct.getAll(Tag.class);
                if (0 < tags.size() && !tags.isEmpty()) {
                    List<String> list = new ArrayList<>();
                    for (Tag tag : tags) {
                        list.add(tag.getString("tag"));
                    }
                    ftags = Util.join(list, ",");
                }
                formulir.put("pid", ct.getId());
                formulir.put("fbook", ct);
                formulir.put("fpub", pbs);
                formulir.put("fcat", cat);
                formulir.put("fauths", fauth);
                formulir.put("ftags", ftags);
                formulir.put("title", "Buku Form");
                formulir.put("method", "POST");
                formulir.put("action", context() + "/1/c/book");

                view("formulir", formulir);
                render("/contents/books/a001");
            } else {
                flash("msgbox", "Record Tidak ditemukan : " + param("ID"));
                redirect(context() + "/1/c/book");
            }
        } catch (DBException ex) {
            render("/system/error", Collections.map("e", ex, "message", ex.getMessage())).layout("system_error");
        }
    }

    /**
     * Ajax with msgBox {DELETE} : /1/c/{ID}/book delete a specific category
     * return void
     */
    @DELETE
    public void d011() {
        // kirim msgBox confirm Record ID delete
        StringBuilder sbmsgbox = new StringBuilder();
        try {
            Book book = MContentBook.ReadByID(param("ID"));
            LazyList<Tag> tags = book.getAll(Tag.class);
            for (Tag tag : tags) {
                book.remove(tag);
            }

            LazyList<Author> auths = book.getAll(Author.class);
            for (Author auth : auths) {
                book.remove(auth);
            }

            StringBuilder sb = new StringBuilder();
            sb.append(getRealPath(""));
            sb.append("/c/");
            if (ZHelper.ziRemovedir(book.getId(), sb)) {
                sbmsgbox.append("Folder Content Sudah Terhapus!!").append(" dan ")
                        .append('\n');
            }
            if (book.delete()) {
                sbmsgbox.append("Record Terhapus : ").append(param("ID")).append("!!!");
            } else {
                sbmsgbox.append("Ada Kesalahan Pada Server Data Tidak Dapat Terhapus");
            }
        } catch (DBException db) {
            logInfo("logging d011");
            sbmsgbox.append(db.getMessage());
            logInfo(sbmsgbox.toString());
            render().noLayout();
        }
        respond(sbmsgbox.toString()).contentType("text/plain").status(200);
    }

    public void dtbl() {
        /*
         *  column field from database
         * */
        String[] aColumns = {"id", "image_url", "isbn", "judul", "lokasi"};
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
        List<Object> sParams = new ArrayList<>();
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
        LazyList<Book> rResult = Book.where(sWhere, sParams.toArray())
                .offset(sStart)
                .limit(sLimit)
                .orderBy(sOrder);

        Long iTotal = Book.count();
        int iFilteredTotal = params().containsKey("sSearch") && !param("sSearch").equals("") ? rResult.size() : iTotal.intValue();
        /*
         * Data aaData Vector
         * */
        List<Object> aaData = new ArrayList<>();
        for (Book book : rResult) {
            ArrayList<Object> ct = new ArrayList<>();
            ct.removeAll(ct);
            for (String sColumn : aColumns) {
                Object tColumn;
                if (sColumn.equalsIgnoreCase("image_url")) {
                    tColumn = "<img style=\" max-width: 135px;max-height: 155px;\" src=\"" + context() + book.get(sColumn) + "\" />";

                } else {
                    tColumn = book.get(sColumn);
                }
                ct.add(tColumn);
            }
            //column tambahan untuk edit dan delete
            String[] colAdj = {
                    "<a href=\"" + context() + "/1/c/" + ct.get(0) + "/book" + "\" class=\"fa fa-file-text\" "
                            + "data-toggle=\"tooltip\" title=\"Edit Action\"></a>",
                    "<a href=\"" + context() + "/1/c/" + ct.get(0) + "/book" + "\" data-method=\"delete\" data-linkto=\"aw\"><i class=\"fa fa-trash-o\"></i></a>"
            };
            ct.add(colAdj[0]);
            ct.add(colAdj[1]);
            aaData.add(ct);
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
        } catch (JsonProcessingException e) {
            logError(e.getMessage());
        }
    }
}
