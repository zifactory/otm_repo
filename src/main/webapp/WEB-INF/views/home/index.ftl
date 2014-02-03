<@content for="title">Kontent Edukasi Indonesia </@content>
<#if contents??>
    <@render partial="/pages/card_wrapper"/>
</#if>
<#if authuser??>
    <@debug print=authuser.getListModul()/>
    <#--${authuser.getListModul().Trading}-->
</#if>
<#--<#list kontent as book>-->
<#--${book.judulKategori}<br/>-->
<#--<#list book.content as cont>-->
<#--${cont.image}<br/>-->
<#--</#list>-->
<#--</#list>-->
<#--<@debug print=kontent/>-->
<#--${id!""}-->
<#--<#if authuser??>-->
<#--${authuser.getTokenS()}-->
<#--&lt;#&ndash;<@debug print=authuser.getUser().password/>&ndash;&gt;-->
<#--</#if>-->

