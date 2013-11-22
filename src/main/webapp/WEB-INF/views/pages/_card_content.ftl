<#--Cluster-Heading-->
<div class="cluster-heading">
${card_content.judulKategori}
    <a class="btn orange see-more" href="">buka semua</a>
</div><#-- end Cluster-Heading-->
<div class="card-list">
<#list card_content.content as kontents>
    <@render partial="/pages/card_book_produk"/>
    </#list>
</div>
