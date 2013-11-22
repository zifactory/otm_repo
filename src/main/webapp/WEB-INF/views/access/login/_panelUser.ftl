<#--if isLogin? -->
<a class="btn orange" href="#" data-toggle="dropdown">
    <img alt="" src="${context_path}/asset/img/nanang_small.png"/>
    <span class="username">${(authuser.getUser().name)!"users"}</span>
</a>


<ul class="dropdown-menu">
    <li><a href="${context_path}/0/${(authuser.getUser().id)!0}">ProfileKu<span class="badge badge-icon"><i
            class="icon-user"></i></span></a></li>
    <li><a href="page_calendar.html"> {IDR100k} Balance<span class="badge badge-icon"><i
            class="icon-calendar"></i></span></a></li>
    <li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox <span class="badge badge-important">3</span></a>
    </li>
    <li><a href="#"><i class="icon-tasks"></i> My Tasks <span class="badge badge-success">8</span></a>
    </li>
    <li class="divider"></li>
    <li><a href="" id="trigger_fullscreen"><i class="icon-move"></i> Full Screen</a></li>
    <li><a href="extra_lock.html"><i class="icon-lock"></i> Lock Screen</a></li>
    <li>
        <form action="${context_path}/x" method="post">
            <div style="margin-left:15px;">
                <input type="submit" class="m-wrap btn orange" name="logout" value="Log Out"/>
            </div>
        </form>
    </li>
</ul>