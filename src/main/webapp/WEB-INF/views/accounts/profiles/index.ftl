<@content for="title">Profile :: ${(getUser.name)!"user"}</@content>
<div class="details-info">
    <div class="cluster">
    <#--Profiles Content-->
        <div class="profile-content">
            <!-- Profiles page -->
            <div class="profile-card">
                <div class="profile-background"
                     style="background-image: url(${context_path}/p/${(getUser.id)!"0"}/i/${(getUser.image_bg)!"logo.png"})">
                    <div class="profile-wrapper">
                        <div class="profile-display">
                            <div class="cover">
                                <div class="cover-image-container">
                                    <div class="cover-outer-align">
                                        <div class="cover-inner-align">
                                            <img class="cover-image"
                                                 src="${context_path}/p/${(getUser.id)!"0"}/i/${(getUser.image_url)!"baby.png"}"/>
                                        </div>
                                    </div>
                                </div>
                                <a href="#">
                                    <span class="preview-overlay-container"></span>
                                </a>
                            </div>
                        </div>
                        <div class="profile-detail">
                            <div class="profile-name">
                                <h1 class="fullname">${(getUser.name)!"user"}</h1>
                            </div>
                            <div class="profile-status">
                                <p>
                                ${(getUser.status)!"Terkadang sunyi itu membunuh ku
                                    sampai jawaban tak pernah bisa tersirat makna sebenarnya
                                    tanpa ada rasa ini tak kunjung bisa selesai"}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Profiles page -->
            <div class="user-activity">
                <div class="cover-inner-align">
                    <img class="cover-image"
                         src="${context_path}/p/${(getUser.id)!"0"}/i/${(getUser.image_url)!"baby.png"}"/>
                </div>
                <div class="cover-inner-align">
                    <img class="cover-image" src="${context_path}/asset/img/baby.jpg"/>
                </div>
            </div>
        </div>
    <#-- End Profiles Content-->
    </div><#-- end cluster-->
</div><#-- end details-info-->