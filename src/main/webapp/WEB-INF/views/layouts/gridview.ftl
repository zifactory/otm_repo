<@content for="css">
<link rel="stylesheet" href="${context_path}/asset/plugins/data-tables/DT_bootstrap.css"/>
</@content>
<@content for="js">
<script type="text/javascript" src="${context_path}/asset/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${context_path}/asset/plugins/data-tables/DT_bootstrap.js"></script>
<script type="text/javascript">
    $(function () {
    <#--for JS Delete before and after-->
        var beforeDel = function () {
            console.log("puck function before delete!!!");
        };

        //===== Dynamic data table =====//
        var initTable = function () {
            var oTable = $('#dTable').dataTable({
                "aoColumnDefs": [
                    { "aTargets": [ 0 ] }
                ],
                "aaSorting": [
                    [1, 'asc']
                ],
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                /*  "sScrollY": 270,
                  "bScrollCollapse": true,*/
                "sDom": "<'row'<'span4'l><'span6'f>r>t<'row'<'span4'i><'span6'p>>"
            });
            $('#dTable_wrapper .dataTables_filter input').addClass("m-wrap large"); // modify table search input
            $('#dTable_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
        };
        initTable();
    });
</script>
</@content>

<div class="cluster">
<#--Cluster-Heading-->
    <div class="cluster-heading">${(gridview.title)!"Tabel Title"}</div><#-- end Cluster-Heading-->
    <div class="card-list">
        <div class="card">
            <div style="margin-bottom: 25px;"><a href="${(gridview.urlAdd)!"add"}" class="btn orange">Tambah Data</a>
            </div>

            <table class="table table-striped table-bordered table-hover table-full-width" id="dTable">
                <thead>
                <tr>
                <#list gridview.colnames as titleCol >
                    <th>${ titleCol!"1" }</th>
                </#list>
                    <th></th>
                    <th></th>
                <#if (gridview.print)?? >
                    <th></th>
                </#if>
                </tr>
                </thead>
                <tbody>
                <#list gridview.columns as col >
                <tr>
                    <#list col as cl >
                        <#if cl_index == 0 >
                            <#assign fid = cl />
                        </#if>
                        <#if !cl_has_next >
                        <#--{# untuk action delete dan edit #}-->
                            <td>${cl!" - "}</td>
                            <td><a href="${ (gridview.urlEdit)!"" }/${ fid!"0" }" class="fa fa-file-text"
                                   data-toggle="tooltip" title="${(gridview.tooltipEdit)!"Edit Action"}"></a></td>
                            <td><a href="${ (gridview.urlDelete)!"" }/${ fid!"0" }" class="fa fa-trash-o"
                                   data-toggle="tooltip" title="${(gridview.tooltipRemove)!"Remove Action"}"
                                   data-method="delete" data-linkto="aw"></a></td>
                            <#if (gridview.print)?? >
                                <td><a name="printid" href="${ (gridview.urlPrint)!"" }/${ fid!"0" }"
                                       class="buttonS bBlack">
                                    <span class="fa fa-print" style="float: none;"></span></a>
                                </td>
                            </#if>
                        <#else >
                            <td>${cl!" - "}</td>
                        </#if>
                    </#list>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>