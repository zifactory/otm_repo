<#setting url_escaping_charset='ISO-8859-1'>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta charset="utf-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <meta name="title" content=""/>
        <meta name="author" content=""/>
        <meta name="owner" content=""/>
        <meta name="subject" content="sport News, Organisation comunnity"/>
        <meta name="rating" content="general"/>
        <meta name="description" content=""/>
        <meta name="abstract" content=""/>
        <meta name="language" content="ID"/>
        <meta name="copyright" content=""/>
        <meta name="robots" content="index, follow"/>
        <meta name="revisit-after" content="30 days"/>
        <meta name="distribution" content="global"/>
        <meta name="content-Language" content="english"/>

        <title>OTransMedia | <@yield to="title"/></title>
        <link href="${context_path}/asset/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

        <link href="${context_path}/asset/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!--[if IE 7]>
        <link href="${context_path}/asset/plugins/font-awesome/css/font-awesome-ie7.min.css" rel="stylesheet"
              type="text/css">
              <![endif]-->
          <#-- for nexus-->
          <#--<link href="${context_path}/asset/plugins/nexus/css/component.css" rel="stylesheet" type="text/css">-->
          <@yield to="css"/>
        <link href="${context_path}/asset/css/nprogress.css" rel="stylesheet" type="text/css">
        <link href="${context_path}/asset/plugins/select2/select2.css" rel="stylesheet" type="text/css">
        <link href="${context_path}/asset/plugins/select2/select2-bootstrap.css" rel="stylesheet" type="text/css">
        <link href="${context_path}/asset/css/pretty-page.css" rel="stylesheet" type="text/css">
        <link href="${context_path}/asset/css/reset.css" rel="stylesheet" type="text/css">

        <link href="${context_path}/asset/css/style.css" rel="stylesheet" type="text/css">
          <#--<link href="${context_path}/asset/css/style.min.css" rel="stylesheet" type="text/css">-->

        <link rel="shortcut icon" href="${context_path}/asset/img/ico/favicon.ico"/>
    </head>
    <body>
        <#include "header.ftl">
        <#--jika administrator ganti -->
        <#assign her=true/>
        <#if her == true>
        <#include "/admin/navbar.ftl" />
        <#else>
        <#include "navbar.ftl">
        </#if>
        <#--${page_content}-->
        <#include "wcontent.ftl">
        <#include "footer.ftl">
    </body>
</html>
