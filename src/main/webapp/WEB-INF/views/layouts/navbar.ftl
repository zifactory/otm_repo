<!-- NAV Search BAR -->
<div class="nsearch front-header">
    <div class="container">
        <div class="row" style="padding:8px;">
         <span class="span5">
              <form id="form-search" action="#" class="form-search">
                  <div class="input-group">
                      <label for="searchKontent"></label>
                      <input type="text" id="searchKontent" class="form-control" placeholder="Pencarian Kontent"/>
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                        </span>
                  </div>
              </form>
              </span>
          <span class="span4">
              <ul class="unstyled inline">
                  <li class="dropdown">
                      <a href="#" data-toggle="dropdown"><h4>Buku <i class="fa fa-angle-down"></i></h4></a>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="fa fa-tasks"></i> My Tasks <span
                                  class="badge badge-success">8</span></a>
                          </li>
                          <li class="divider"></li>
                          <li><a href="" id="trigger_fullscreen"><i class="icon-move"></i> Full Screen</a></li>
                          <li><a href="extra_lock.html"><i class="icon-lock"></i> Lock Screen</a></li>
                          <li><a href="login.html"><i class="icon-key"></i> Log Out</a></li>
                      </ul>
                  </li>
                  <li class="dropdown">
                      <a href="#" data-toggle="dropdown"><h4>Aplikasi / Games <i class="fa fa-angle-down"></i></h4></a>
                      <ul class="dropdown-menu">
                          <li><a href="#"><i class="icon-tasks"></i> My Tasks <span class="badge badge-success">8</span></a>
                          </li>
                          <li class="divider"></li>
                          <li><a href="" id="trigger_fullscreen"><i class="icon-move"></i> Full Screen</a></li>
                          <li><a href="extra_lock.html"><i class="icon-lock"></i> Lock Screen</a></li>
                          <li><a href="login.html"><i class="icon-key"></i> Log Out</a></li>
                      </ul>
                  </li>
                  <li><a href="#"><h4>Video <i class="fa fa-angle-down"></i></h4></a></li>
              </ul>
         </span>
        </div>
    </div>
<#--nav menu for content setiap user -->
<#--module publisher || admin -->
<#if (authuser.getListModul().inv)?? && (actUser.menu_content)??>
    <div class="nav-container"
         style="visibility: visible;background-color: rgba(245, 245, 245, 0.3);">
        <form id="form-search-kontent" action="#">
            <div class="input-group">
                <label for="search-content"></label>
                <input type="text" id="search-content" class="form-control" placeholder="Cari Apps/Games Kontent"/>
            <span class="input-group-btn"><button class="btn btn-default" type="button"><i class="fa fa-search"></i>
            </button></span>
            </div>
        </form>
        <ul class="nav nav-pills nav-stacked">
            <li id="book-nav"><a href="${context_path}/1/content/${(authuser.getUser().id)!0}/books"
                                 class="nav-content btn blue" title="Buku" data-color="blue"
                                 data-id="${(authuser.getUser().id)!0}" data-destination="cards-contents"
                                 data-link="aw"><i class="fa fa-2x fa-book fa-fw"></i>Books</a></li>
            <li id="app-nav"><a href="${context_path}/1/content/${(authuser.getUser().id)!0}/apps"
                                class="nav-content btn green" title="App/Games" data-color="green"
                                data-id="${(authuser.getUser().id)!0}" data-destination="cards-contents" data-link="aw"><i
                    class="fa fa-2x fa-laptop fa-fw"></i>Apps / Games</a></li>
            <li id="video-nav"><a href="${context_path}/1/content/${(authuser.getUser().id)!0}/vids"
                                  class="nav-content btn red" title="Video" data-color="red"
                                  data-id="${(authuser.getUser().id)!0}" data-destination="cards-contents"
                                  data-link="aw"><i class="fa fa-2x fa-film fa-fw"></i>Videos</a></li>
            <li id="create-content" style="display: none;"><a class="btn green" href="#"
                                                              data-id="${(authuser.getUser().id)!0}"
                                                              data-destination="cards-contents"
                                                              data-link="aw">
                <i class="fa fa-2x fa-file-text fa-fw"></i>Create Content</a>
            </li>
        </ul>
    </div>
</#if>
<#--End nav menu-->
</div>
<!-- END NAV SEARCH BAR -->