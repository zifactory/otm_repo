$(function () {
    var bag = ($(window).height() / 100) * 15;
    //+++++ Nav fixed scroll +++++//
    $(window).on("scroll", function () {
        if ($(window).scrollTop() > bag) {
            $('.nsearch').removeClass('front-header');
            $('.nsearch').addClass('navbar navbar-fixed-top border-shadow bg-white');

            $('#user-akun a.btn').addClass('mini');
            $('#user-akun .btncart').addClass('mini');
            $('#user-akun').addClass('no-margin-top');
            $('#user-akun').appendTo('.nsearch .row');
        } else {
            $('.nsearch').removeClass('navbar navbar-fixed-top border-shadow bg-white');
            $('.nsearch').addClass('front-header');

            $('#user-akun a.btn').removeClass('mini');
            $('#user-akun .btncart').removeClass('mini');
            $('#user-akun').removeClass('no-margin-top');
            $('#user-akun').appendTo('.ntopbar.row');
        }
    });
    //===== Chosen plugin =====//
    $(".select").chosen({
        no_results_text: "Oops, nothing found!"
    }).change(function (e) {
            var valOpt = $(this).val();
            console.log(valOpt);
            $("div.selectvalue").html('<h3>' + valOpt + '</h3>');
        });
});