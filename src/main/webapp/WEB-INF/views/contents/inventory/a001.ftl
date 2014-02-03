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
            <form action="${(formulir.action)!"#"}" method="${(formulir.method)!"POST"}" style="width:350px;">
                <div class="form-group">
                    <label for="Judul">Category Name</label>
                    <input type="text" class="form-control" name="judul" id="Judul"
                           placeholder="Enter Category Name" value="${(formulir.fdesk.judul)!""}"/>
                </div>
                <div class="form-group">
                    <label for="Deskripsi">Deskripsi : </label>
                    <input type="text" class="form-control" name="deskripsi" id="Deskripsi"
                           placeholder="Keterangan Buku"
                           value="${(formulir.fdesk.deskripsi)!""}"/>
                </div>
                <div class="form-group">
                    <label for="Deskripsi">Deskripsi : </label>
                    <input type="text" class="form-control" name="deskripsi" id="Deskripsi"
                           placeholder="Keterangan Buku"
                           value="${(formulir.fdesk.deskripsi)!""}"/>
                </div>
                <input type="hidden" name="pid" value="${(formulir.pid)!"0"}"/>
                <button type="submit" name="save" class="btn btn-default"><i class="fa fa-save fa-fw"></i>Save</button>
                <button type="submit" name="cancel" class="btn btn-default"><i class="fa fa-ban fa-fw"></i>Cancel
                </button>
            </form>
        </div>
    </div>
</div>