<div class="cluster">
<#--Cluster-Heading-->
    <div class="cluster-heading">
        <div class="btn btn-lg">
            <i class="fa fa-anchor fa-fw pull-left"></i>Record ${(formulir.pid)!"For Record ID"}
        </div>
    ${(formulir.title)!"Category Form"}
    </div><#-- end Cluster-Heading-->
    <div class="card-list">
        <div class="card">
            <form action="${(formulir.action)!"#"}" method="${(formulir.method)!"POST"}" style="width:350px;">
                <div class="form-group">
                    <label for="groupname">Group Name</label>
                    <input type="text" class="form-control" name="nama" id="groupname"
                           placeholder="Enter Group Name" value="${(formulir.fgrp.nama)!""}">
                </div>

                <input type="hidden" name="pid" value="${(formulir.pid)!"0"}"/>
                <button type="submit" name="save" class="btn btn-default"><i class="fa fa-save fa-fw"></i>Save</button>
                <button type="submit" name="cancel" class="btn btn-default"><i class="fa fa-ban fa-fw"></i>Cancel
                </button>
            </form>
        </div>
    </div>
</div>