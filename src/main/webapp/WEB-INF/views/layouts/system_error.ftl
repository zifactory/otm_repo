<#setting url_escaping_charset='ISO-8859-1'>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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

    <title>OTransMedia | <@yield to="title"/> </title>
    <link href="${context_path}/asset/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${context_path}/asset/css/reset.css" rel="stylesheet" type="text/css">
    <link href="${context_path}/asset/css/style.css" rel="stylesheet" type="text/css">
    <link href="${context_path}/asset/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${context_path}/asset/css/pretty-page.css">
    <!--[if IE 7]>
    <link href="${context_path}/asset/plugins/font-awesome/css/font-awesome-ie7.min.css" rel="stylesheet"
          type="text/css">
    <![endif]-->
</head>
<body>
<!-- BEGIN TOP BAR -->
<div class="front-topbar">
    <div class="container">
        <div class="ntopbar row">
            <div class="span2">
                <!-- BEGIN LOGO (you can use logo image instead of text)-->
                <a class="brand logo-v1" href="${context_path}">
                    <img src="${context_path}/asset/img/logo.png" id="logoimg" alt=""/>
                </a>
                <!-- END LOGO -->
            </div>
            <div class="span9">
            <span>
              <ul class="unstyled inline">
                  <li><i class="fa fa-phone topbar-info-icon top-2"></i>Hubungi Kami: <span>(+6231) 456 6717</span></li>
                  <li class="sep"><span>|</span></li>
                  <li><i class="fa fa-envelope topbar-info-icon top-2"></i>Email: <span>info@otransmedia.co.id</span>
                  </li>
              </ul>
              <ul class="unstyled inline pull-right">
                  <li><a href="#"><i class="fa fa-home"></i>Beranda</a></li>
                  <li><a href="#"><i class="fa fa-book"></i>Produk</a></li>
                  <li><a href="#"><i class="fa fa-google-plus"></i>Layanan</a></li>
                  <li><a href="#"><i class="fa fa-phone"></i>Kontak Kami</a></li>
                  <li><a href="${context_path}/3/cats/009" data-method="delete" data-link="aw"><i
                          class="fa fa-users"></i>Tentang Kami</a></li>
              </ul>
          </span>

                <div class="inline">
                    <span class="span4"><h1>OTransMedia</h1></span>
                    <span class="span5"><h4>Layanan Kontent Edukasi Indonesia</h4></span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END TOP BAR -->
<#include "navbar.ftl">

<#--<@debug print=session/>-->
<#--${env!}-->
<#assign evn = env!'development'/>
<#if evn != "development">
    <@yield to="errormsg"/>
<#else >
    <@yield to="errorHuman"/>
</#if>
<#include "footer.ftl">
</body>
</html>