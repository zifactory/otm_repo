<@content for="css">
<link rel="stylesheet" href="${context_path}/asset/plugins/data-tables/DT_bootstrap.css"/>
</@content>
<@content for="js">
<script type="text/javascript" src="${context_path}/asset/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${context_path}/asset/plugins/data-tables/DT_bootstrap.js"></script>
<script type="text/javascript" charset="utf-8">
    $(function () {
    <#--for JS Delete before and after-->

        //===== Dynamic data table =====//
        var initTable = function () {
            var oTable = $('#dTable').dataTable({
                bRetrieve: true,
                "aoColumnDefs": [
                    { "aTargets": [ 0 ] }
                ],
                "aaSorting": [
                    [1, 'asc']
                ],
                "aLengthMenu": [
                    [5, 10, 20, 100],
                    [5, 10, 20, 100] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row' r<'span4'l><'span6'f>>t<'row'<'span4'i><'span6'p>>",
                "bProcessing": true,
                "bServerSide": true,
                "fnRowCallback": function (nRow, aData, iDisplayIndex) {
                    $('td:eq(-1)', nRow).addClass('ok_rock');
                    return nRow;
                },
                "fnDrawCallback": function () {
                    $("a[data-linkto]").bind("click", function () {
                        console.log("One Ok Rock");
                        var anchor = $(this);
                        var destination = anchor.attr("data-destination");

                        var href = anchor.attr("href");
                        var _method = anchor.attr("data-method");
                        var before = anchor.attr("data-before");
                        var after = anchor.attr("data-after");

                        if (_method == null) {
                            _method = "get";
                        }
                        var type;
                        if (_method.toLowerCase() == "get") {
                            type = "get";
                        } else if (_method.toLowerCase() == "post"
                                || _method.toLowerCase() == "put"
                                || _method.toLowerCase() == "delete") {
                            type = "post";
                        }

                        var data = "_method=" + _method;

                        bootbox.confirm("Anda Yakin untuk menghapus record?", function (result) {
                            if (result == true) {
                                $.ajax({
                                    xhr: function (activexobject, xhr) {
                                        var xhr = new window.XMLHttpRequest();
                                        //Upload progress
                                        xhr.upload.addEventListener("progress", function (evt) {
                                            if (evt.lengthComputable) {
                                                var percentComplete = evt.loaded / evt.total;
                                                //Do something with upload progress
                                                NProgress.set(percentComplete);
                                            }
                                        }, false);
                                        //Download progress
                                        xhr.addEventListener("progress", function (evt) {
                                            if (evt.lengthComputable) {
                                                var percentComplete = evt.loaded / evt.total;
                                                //Do something with download progress
                                                NProgress.set(percentComplete);
                                            }
                                        }, false);
                                        return xhr;
                                    },
                                    url: href, data: data, type: type,
                                    beforeSend: function (xhr) {
                                        NProgress.start();
                                        console.log("ajax proses");
                                    },
                                    success: function (data) {
                                        if (after != null)
                                            eval(after)(afterArg, data);

                                        if (destination != null)
                                            $("#" + destination).html(data);
                                        //var oTable = $('#dTable').dataTable({});
                                        $('#dTable').notify(data, { position: "top center", className: "success" });
                                        oTable.fnDraw();
                                        NProgress.done();
                                    },
                                    error: function (xhr, status, errorThrown) {
                                        if (error != null) {
                                            eval(error)(xhr.status, xhr.responseText);
                                        }
                                        NProgress.done();
                                    }
                                });
                            }
                        });
                        return false;
                    });
                },
                "sAjaxSource": "${(gridview.ajaxSource)}",
                "fnServerData": function (sUrl, aoData, fnCallback, oSettings) {
                    oSettings.jqXHR = $.ajax({
                        xhr: function (activexobject, xhr) {
                            var xhr = new window.XMLHttpRequest();
                            //Upload progress
                            xhr.upload.addEventListener("progress", function (evt) {
                                if (evt.lengthComputable) {
                                    var percentComplete = evt.loaded / evt.total;
                                    //Do something with upload progress
                                    NProgress.set(percentComplete);
                                }
                            }, false);
                            //Download progress
                            xhr.addEventListener("progress", function (evt) {
                                if (evt.lengthComputable) {
                                    var percentComplete = evt.loaded / evt.total;
                                    //Do something with download progress
                                    NProgress.set(percentComplete);
                                }
                            }, false);
                            return xhr;
                        },
                        beforeSend: function (xhr) {
                            NProgress.start();
                        },
                        "url": sUrl,
                        "data": aoData,
                        "success": [fnCallback, function () {
                            NProgress.done();
                        }],
                        "dataType": "jsonp",
                        "cache": false
                    });
                }
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
                <tr>
                    <td colspan="5" class="dataTables_empty">Loading data from server</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>