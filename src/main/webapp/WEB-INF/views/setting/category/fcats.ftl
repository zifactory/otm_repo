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
                    <label for="categoryname">Category Name</label>
                    <input type="text" class="form-control" name="keterangan" id="categoryname"
                           placeholder="Enter Category Name" value="${(formulir.fcats.keterangan)!""}">
                </div>
                <div class="form-group">
                    <label for="subcategory">Sub Category</label>
                    <select data-placeholder="Sub Category" name="root" id="subcategory"
                            class="select form-control" tabindex="10">

                        <option value=""></option>
                    <#assign cid = (formulir.catid)!0/>
                    <#list formulir.cats as cat >
                        <#if cat_index == 0 && cid == 0 >
                        <option selected="selected"
                                value=""> Root
                        </option>
                        <#elseif cat.id == cid >
                        <option selected="selected"
                                value="${ cat.id }"> ${ cat.keterangan } | ${ cat.id }
                        </option>
                        <#else>
                        <option value="${ cat.id }"> ${ cat.keterangan } | ${ cat.id }</option>
                        </#if>
                        </#list>
                    </select>
                </div>
                <input type="hidden" name="pid" value="${(formulir.pid)!"0"}"/>
                <button type="submit" name="save" class="btn btn-default"><i class="fa fa-save fa-fw"></i>Save</button>
                <button type="submit" name="cancel" class="btn btn-default"><i class="fa fa-ban fa-fw"></i>Cancel
                </button>
            </form>
        </div>
    </div>
</div>