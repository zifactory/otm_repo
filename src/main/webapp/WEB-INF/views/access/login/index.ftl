<div class="login">
    <div class="cluster">
        <div class="card-list">
            <div class="card">
                <div class="login-content">
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
                                <div class="checker"><span><input type="checkbox" name="rememberme" value="true"></span>
                                </div>
                                Remember Me
                                <label class="label">
                                    <a href="#"><i class="icon-check-sign"></i> forget password?</a>
                                </label>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="card"
                                 style="color: #428bca;width:370px;height:80px;padding:4px;-webkit-border-radius: 2px;border-radius: 2px;-moz-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);-ms-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);-webkit-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                                Selamat Datang dilayanan kontent edukasi indonesia, Silakan <span
                                    style="color: orangered;">Sign up now</span> untuk bergabung dengan
                                OTransMedia
                            </div>
                        </div>
                    </div>
                    <div class="signup-form"
                         style="margin-left: 398px;background: right no-repeat url('${context_path}/asset/img/bgloginok_opa.png')">
                        <blockquote>
                            <div class="control-group">
                                <div class="input-icon left">
                                    <i class="icon-user"></i><input type="text" class="m-wrap" name="name"
                                                                    placeholder="Firstname Lastname"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="input-icon left">
                                    <i class="icon-envelope"></i><input type="text" class="m-wrap" name="email"
                                                                        placeholder="Email"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="input-icon left">
                                    <i class="icon-lock"></i><input type="password" class="m-wrap" name="password"
                                                                    placeholder="Password"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="input-icon left">
                                    <i class="icon-warning-sign"></i><input type="password" class="m-wrap"
                                                                            name="confirmpass"
                                                                            placeholder="Confirm Password"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="input-icon left">
                                    <i class="icon-share-sign"></i><input type="submit" class="m-wrap btn orange"
                                                                          name="signup" value="Sign up now"/>
                                </div>
                            </div>
                        </blockquote>
                    </div>
                </@form>
                </div>
            </div>
        </div>
    </div>
</div>