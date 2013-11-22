<@content for="title">Kontent Edukasi Indonesia </@content>
<#--<@render partial="/pages/card_wrapper" />-->
<#--<#list kontent as book>-->
<#--${book.judulKategori}<br/>-->
<#--<#list book.content as cont>-->
<#--${cont.image}<br/>-->
<#--</#list>-->
<#--</#list>-->
<#--<@debug print=kontent/>-->
${id!""}
<#if authuser??>
${authuser.getTokenS()}
<#--<@debug print=authuser.getUser().password/>-->
</#if>

