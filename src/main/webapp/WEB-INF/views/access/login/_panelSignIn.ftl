<a class="btncart btn orange" href="#" data-toggle="dropdown">
    <i class="icon-bitbucket-sign"></i>
    <span class="username">Login</span>
    <i class="icon-angle-down"></i>
</a>
<ul class="dropdown-menu">
    <li>
        <div class="login">
        <@form controller="/access/login" action="signin" method="POST">
            <div class="login-form" style="position:relative;float: left;">
                <div class="control-group">
                    <div class="input-icon left">
                        <i class="icon-user"></i><input type="text" class="m-wrap" name="nameoremail"
                                                        placeholder="Username Or Email"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="input-icon left">
                        <i class="icon-key"></i><input type="text" class="m-wrap" name="password"
                                                       placeholder="Password"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="input-icon left">
                        <i class="icon-signin"></i><input type="submit" class="m-wrap btn orange"
                                                          name="signin" value="Sign In"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="checkbox left">
                        <div class="checker"><span><input type="checkbox" name="rememberme" value=""></span>
                        </div>
                        <span>Remember me</span>
                        <label class="label">
                            <@link_to controller="/access/login" action="forgotten"><i class="icon-check-sign"></i>
                                forget
                                password?</@link_to>
                        </label>
                    </div>
                </div>
            </div>
        </@form>
        </div>
    </li>
<#--<li><a href="#"><i class="icon-gears"></i><span>New Member? Sign Up</span></a></li>-->
    <li><@link_to controller="/access/login"  class="link_blue" action=""><i
            class="icon-gears"></i><span>New Member? Sign up now</span></@link_to></li>

</ul>