<div class="details-info">
    <div class="cluster">
    <#-- <#list contents as cntx>
         <h3>${cntx.category_name}</h3><br>
         <#list cntx.contents as content>
             <#list content.content_css as css>
                 <h4>${css}</h4><br/>
             </#list>
             <h4>${content.content_bean.judul}</h4><br/>
             <h4>${content.content_bean.author}</h4><br/>
             <h4>${content.content_bean.point}</h4><br/>
         </#list>
     </#list>-->
    <#--<@debug print=contents />-->
    <@render partial="/pages/card_content" collection=contents/>
    </div><#-- end cluster-->
</div><#-- end details-info-->