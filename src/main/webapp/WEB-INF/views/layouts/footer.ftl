<div class="front-copyright bg-white navbar-fixed-bottom">
    <div class="container">
        <!--  <p class="navbar-text pull-left"> , <span> Develop </span></p> -->
         <span class="pull-left">
          <ul class="unstyled inline">
              <li>&copy; 2013 <a href="#">PT.Nusaraya Bhaskara jaya</a></li>
              <li>Powered by <a href="#">Zifactory &#8482; team</a></li>
          </ul>
        </span>
        <span class="pull-right">
          <ul class="unstyled inline">
              <li><a href="#">Syarat Dan Ketentuan</a></li>
              <li><a href="#">Kebijakan Privasi</a></li>
              <li><a href="#"><i class="fa fa-facebook"></i></a></li>
              <li><a href="#"><i class="fa fa-twitter"></i></a></li>
          </ul>
        </span>
    </div>
</div><!-- end front-copyright  -->
<script src="${context_path}/asset/scripts/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${context_path}/asset/scripts/jquery-1.10.1.min.map.js" type="text/javascript"></script>
<script src="${context_path}/asset/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${context_path}/asset/plugins/chosen/chosen.jquery.min.js" type="text/javascript"></script>
<script src="${context_path}/asset/scripts/kopi/nprogress.js" type="text/javascript"></script>
<@yield to="js"/>
<script src="${context_path}/asset/scripts/kopi/bootbox.min.js" type="text/javascript"></script>
<script src="${context_path}/asset/scripts/kopi/notify.min.js" type="text/javascript"></script>
<script src="${context_path}/asset/scripts/kopi/aw.min.js" type="text/javascript"></script>
<script src="${context_path}/asset/scripts/kopi/punk.js" type="text/javascript"></script>
<#--<script src="${context_path}/asset/scripts/kopi/script.min.js" type="text/javascript"></script>-->
<script type="text/javascript">
    NProgress.start();
    setTimeout(function () {
        NProgress.done();
    }, 1000);
</script>
