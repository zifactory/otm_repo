<#--Cluster-Heading-->
<div class="cluster-heading">
${card_content.category_name}
    <a class="btn orange see-more" href="">buka semua</a>
</div><#-- end Cluster-Heading-->
<div class="card-list">
<#list card_content.contents as content>
    <@render partial="/pages/card_content_avatar"/>
    </#list>
</div>
