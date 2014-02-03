$(function () {
    var bag = (($(window).height() - $(window).scrollTop()) / 100) * 15;
    console.log(bag);
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
    /* $(".select").chosen({
     no_results_text: "Oops, nothing found!"
     }).change(function(e) {
     var valOpt = $(this).val();
     console.log(valOpt);
     $("div.selectvalue").html('<h3>' + valOpt + '</h3>');
     });*/
    //====== Select2 plugin ======//
    $(".select").select2();

    var selectFunk = function (selector, url, ph) {
        $(selector).select2({
            placeholder: ph,
            minimumInputLength: 2,
            tokenSeparators: [","],
            tags: true,
            multiple: true,
            createSearchChoice: function (term) {
                return {id: term, text: term};
            },
            ajax: {
                url: gCth + url,
                dataType: 'json',
                quietMillis: 100,
                data: function (term, page) {
                    return {
                        q: term, //search term
                        page_limit: 10, // page size
                        page: page // page number
                        //apikey: "ju6z9mjyajq2djue3gbvv26t"
                    };
                },
                results: function (data, page) {
                    return {results: data.dataset};
                }
            },
            initSelection: function (element, callback) {
                var data = [];
                $(element.val().split(",")).each(function () {
                    data.push({id: this, text: this});
                });
                callback(data);
            }
        });
    };
    selectFunk("#auth", '/s/auths', 'Cari Pengarang');
    selectFunk("#tag", '/s/tags', 'Tags');
    //===== nav-content search plugin =====//
    $(".nav-content").bind('click', function () {
        $("#create-content").hide();
        console.log($(this).attr("data-color"));
        console.log($("#create-content").children());
        var createID = $("#create-content").children();
        var color = $(this).attr("data-color");
        var url = $(this).attr("href");
        var title = $(this).attr("title");
        var ID = $(this).attr("data-id");
        $("#search-content").attr("placeholder", "Cari " + title + " Kontent");
        $("#create-content").slideDown(1000);
        createID.removeClass();
        createID.attr("href", url + "/add");
        createID.addClass("btn " + color);
    });

    //===== ckeditor ===================//
    $('textarea').ckeditor();
    //===== autocomplete typehead =====//

    //typeahead Controller
    $("input[data-th=cats]").typeahead([
        {
            name: 'category_book',
            remote: gCth + '/s/cats',
            prefetch: gCth + '/s/cats',
            template: '{{id}}<strong>{{value}}</strong>',
            engine: Hogan,
            limit: 10
        }
    ]);
    $("input[data-th=pubs]").typeahead([
        {
            name: 'publisher_book',
            remote: gCth + '/s/pubs',
            prefetch: gCth + '/s/pubs',
            template: '{{id}}<strong>{{value}}</strong>',
            engine: Hogan,
            limit: 10
        }
    ]);
    $("input[data-th=auths]").typeahead([
        {
            name: 'author_book',
            remote: gCth + '/s/auths',
            prefetch: gCth + '/s/auths',
            template: '{{id}}<strong>{{value}}</strong>',
            engine: Hogan,
            limit: 10
        }
    ]);
});