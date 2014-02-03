<div class="card
<#list content.content_css as css>
${css!"no-css"}
</#list>">
    <div class="card-content">
        <div class="cover">
            <div class="cover-image-container">
                <div class="cover-outer-align">
                    <div class="cover-inner-align">
                        <img class="cover-image"
                             src="${context_path}/c/0/i/0.png"/></div>
                </div>
            </div>
            <a href="#">
                <span class="preview-overlay-container"></span>
            </a>
        </div>

        <div class="detail">
            <a class="title" href="#">${(content.content_bean.judul)!"Judul Kontent"}</a>
            <div class="subtitle-container">
                <a class="subtitle" href="#">
                <#if (content.content_bean.publisher)??>
                ${(content.content_bean.publisher)!"Publisher"}
                <#elseif (content.content_bean.author)??>
                ${(content.content_bean.author)!"author"}
                <#else>
                    noname
                </#if>
                </a>
            </div>
            <div class="description">${(content.content_bean.desc)!"Deskripsi"}</div>
        </div><#--<!-- end details-->
        <div class="reason-set">
          <span class="stars-container">
            <a href="#">
                <div class="star-no-current fa fa-star">
                    <div class="star-current fa-star-empty"></div>
                </div>
            </a>
            <span class="price-container">${(content.content_bean.point)!"free"}</span>
          </span>
        </div><#--<!-- end reason-set-->
    </div>
</div><#--<!-- end small-->