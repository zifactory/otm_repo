${meme!"loh me"}
${(gridview.print)!"coba coba keras"}
<#if (gridview.print)??>
    <@debug print=gridview.print/>
</#if>
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
            <td>${cl!"-"}</td>
        </#list>
        <td></td>
        <td></td>
        <#if (gridview.print)?? >
            <td></td>
        </#if>
    </tr>
    </#list>
    </tbody>
</table>
<#list gridview.columns as col >
    <#list col as cl >
        <#if cl_index == 0 >
        ${cl} + "00"
        <#elseif !cl_has_next >
        ${cl} + "66"
        <#else>
        ${cl}
        </#if>
    </#list>
</#list>
