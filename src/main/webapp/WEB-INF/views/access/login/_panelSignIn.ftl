<a class="btncart btn orange" href="#" data-toggle="dropdown">
    <i class="fa fa-bitbucket"></i>
    <span class="username">Login</span>
    <i class="fa fa-angle-down"></i>
</a>
<ul class="dropdown-menu">
    <li>
        <div class="login">
            <form action="${context_path}/3/l/signin" method="POST">
                <div class="login-form" style="position:relative;float: left;">
                    <div class="control-group">
                        <div class="input-icon left">
                            <i class="fa fa-user"></i><input type="text" class="m-wrap" name="nameoremail"
                                                             placeholder="Email"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="input-icon left">
                            <i class="fa fa-key"></i><input type="password" class="m-wrap" name="password"
                                                            placeholder="Password"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="input-icon left">
                            <i class="fa fa-sign-in"></i><input type="submit" class="m-wrap btn orange"
                                                                name="signin" value="Sign In"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="checkbox left">
                            <div class="checker"><span><input type="checkbox" name="rememberme" value=""></span>
                            </div>
                            <span>Remember me</span>
                            <label class="label">
                                <a href="${context_path}/3/cats/009" data-method="delete" data-link="aw">
                                    <i class="fa fa-sign-out"></i>forget password?</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </li>
<#--<li><a href="#"><i class="icon-gears"></i><span>New Member? Sign Up</span></a></li>-->
    <li><a href="${context_path}/3" class="link_blue"><i class="fa fa-gears"></i><span>New Member? Sign up now</span></a></li>
</ul>