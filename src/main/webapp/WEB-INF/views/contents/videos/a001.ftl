<div class="cluster">
<#--Cluster-Heading-->
    <div class="cluster-heading">
        <div class="btn btn-lg">
            <i class="fa fa-anchor fa-fw pull-left"></i>Record ${(formulir.pid)!"For Record ID"}
        </div>
    ${(formulir.title)!"Content Book Form"}
    </div><#-- end Cluster-Heading-->
    <div class="card-list">
        <div class="card">
            <form action="${(formulir.action)!"#"}" method="${(formulir.method)!"POST"}" enctype="multipart/form-data">
                <div class="span3">
                    <div class="form-group">
                        <label for="Judul">Judul Video : </label>
                        <input type="text" class="form-control" name="judul" id="Judul"
                               placeholder="Judul Video" value="${(formulir.fvideo.judul)!""}"/>
                    </div>
                    <div class="form-group">
                        <label for="penerbit">Penerbit : </label>
                        <input type="text" class="form-control" name="penerbit_nama" id="penerbit"
                               placeholder="Penerbit Buku" data-th="pubs"
                               value="${(formulir.fpub.nama)!""}" autocomplete="off"/>
                        <input type="hidden" class="form-control" name="penerbit_id"
                               value="${(formulir.fpub.id)!""}"/>
                    </div>
                    <div class="form-group">
                        <label for="auth">Pengarang : </label>
                        <input type="hidden" class="form-control" name="pengarang_nama" id="auth"
                               value="${(formulir.fauths)!""}" autocomplete="off"/>
                    </div>
                    <div class="form-group">
                        <label for="kategori">Kategori : </label>
                        <input type="text" class="form-control" name="kategori_nama" id="kategori"
                               placeholder="Kategori Buku" data-th="cats"
                               value="${(formulir.fcat.keterangan)!""}" autocomplete="off"/>
                        <input type="hidden" class="form-control" name="kategori_id"
                               value="${(formulir.fcat.id)!""}"/>
                    </div>
                    <div class="form-group">
                        <label for="tag">Tag Video: </label>
                        <input type="hidden" class="form-control" name="tag_nama" id="tag"
                               value="${(formulir.ftags)!""}" autocomplete="off"/>
                    </div>
                    <div class="form-group">
                        <label for="Durasi">Durasi : </label>
                        <input type="text" class="form-control" name="durasi" id="Durasi"
                               placeholder="Durasi Video"
                               value="${(formulir.fvideo.durasi)!""}"/>
                    </div>
                    <div class="form-group">
                        <label for="file_content">Upload File Video :</label>
                        <input type="file" class="form-control" name="lokasi" id="cover_content"
                               value="${(formulir.fvideo.lokasi)!""}"/>
                        <span>${(formulir.fvideo.lokasi)!"no-file"}<i class="fa fa-file-text fa-fw"></i></span>
                    </div>
                </div>
                <div class="span4">
                    <div class="form-group">
                        <div><img class="cover-content" src="${context_path}${(formulir.fvideo.image_url)!""}"/></div>
                        <label for="cover_content">Upload Cover Video :</label>
                        <input type="file" class="form-control" name="image_url" id="cover_content"
                               value="${(formulir.fvideo.image_url)!""}"/>
                    </div>
                </div>
                <div class="span8">
                    <div class="form-group">
                        <label for="Deskripsi">Deskripsi : </label>
                        <textarea rows="4" class="form-control" name="deskripsi" id="Deskripsi"
                                  placeholder="Keterangan Video">${(formulir.fvideo.deskripsi)!""}</textarea>
                    </div>
                    <input type="hidden" name="pid" value="${(formulir.pid)!"0"}"/>
                    <button type="submit" name="save" class="btn btn-default"><i class="fa fa-save fa-fw"></i>Save
                    </button>
                    <button type="submit" name="cancel" class="btn btn-default"><i class="fa fa-ban fa-fw"></i>Cancel
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>