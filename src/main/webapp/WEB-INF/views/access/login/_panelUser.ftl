<#--if isLogin? -->
<a class="btn orange" href="#" data-toggle="dropdown">
    <img alt="" src="${context_path}/asset/img/nanang_small.png"/>
    <span class="username">${(authuser.getUser().name)!"users"}</span>
</a>


<ul class="dropdown-menu">
    <li><a href="${context_path}/0/${(authuser.getUser().id)!0}"><i
            class="fa fa-user"></i> - Profile<span class="badge badge-icon"></span></a></li>
    <li><a href="#"><i class="fa fa-home"></i> - Trading<span class="badge badge-important">3</span></a></li>
    <li><a href="#"><i class="fa fa-hdd"></i> - Inventory<span class="badge badge-important">3</span></a></li>
    <li><a href="#"><i class="fa fa-md"></i> - Friends<span class="badge badge-important">3</span></a></li>

    <li><a href="#"><i
            class="fa fa-money"></i> - Balance {IDR100k}<span class="badge badge-icon"></span></a></li>
    <li>
        <form action="${context_path}/x" method="post">
            <div style="margin-left:15px;">
                <input type="submit" class="m-wrap btn orange" name="logout" value="Log Out"/>
            </div>
        </form>
    </li>
</ul>