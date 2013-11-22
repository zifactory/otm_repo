<!-- BEGIN TOP BAR -->
<div class="front-topbar">
    <div class="container">
        <div class="ntopbar row">
            <div class="span2">
                <!-- BEGIN LOGO (you can use logo image instead of text)-->
                <a class="brand logo-v1" href="index.html">
                    <img src="${context_path}/asset/img/logo.png" id="logoimg" alt=""/>
                </a>
                <!-- END LOGO -->
            </div>
            <div class="span9">
            <span>
              <ul class="unstyled inline">
                  <li><i class="icon-phone topbar-info-icon top-2"></i>Hubungi Kami: <span>(+6231) 456 6717</span></li>
                  <li class="sep"><span>|</span></li>
                  <li><i class="icon-envelope-alt topbar-info-icon top-2"></i>Email: <span>info@otransmedia.co.id</span>
                  </li>
              </ul>
             <ul class="unstyled inline pull-right">
                 <li><a href="#"><i class="icon-facebook"></i>Beranda</a></li>
                 <li><a href="#"><i class="icon-twitter"></i>Produk</a></li>
                 <li><a href="#"><i class="icon-google-plus"></i>Layanan</a></li>
                 <li><a href="#"><i class="icon-linkedin"></i>Kontak Kami</a></li>
                 <li><a href="#"><i class="icon-youtube"></i>Tentang Kami</a></li>
             </ul>
          </span>

                <div class="inline">
                    <span class="span4"><h1>OTransMedia</h1></span>
                    <span class="span5"><h4>Layanan Kontent Edukasi Indonesia</h4></span>
                </div>
            </div>

            <div id="user-akun" class="dropdown pull-right">
                <div class="btncart btn red">
                    <i class="icon-shopping-cart"></i>
                    <span class="count-cart">- { Rp1,000,000 }</span>
                </div>
            <#--<#if authuser??>-->
            <#if (authuser.isAuth())??>
                <@render partial="/access/login/panelUser" />
            <#--</#if>-->
            <#else >
                <@render partial="/access/login/panelSignIn"/>
            </#if>
            </div>
            <!-- end #user-akun-->
        </div>
    </div>
</div>
<!-- END TOP BAR -->