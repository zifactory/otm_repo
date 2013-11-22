<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>OTransMedia | <@yield to="title"/></title>

    <link rel="stylesheet" type="text/css" href="${context_path}/asset/css/pretty-page.css">
</head>
<body>
<div class="container">

    <div class="stack-container">

        <div class="frames-container cf">
        <#list Stacks.getStackTrace() as stack>
            <div class="frame <#if stack_index == 0 >active</#if>" id="frame-line-${stack_index!"0"}">
                <div class="frame-method-info">
                    <span class="frame-class">${(stack.getClassName())!""}</span>
                    <span class="frame-function">${(stack.getMethodName())!""}</span>
                </div>

              <span class="frame-file">
               ${(stack.getFileName())!""}
                   <span class="frame-line">${(stack.getLineNumber())!""}</span>
              </span>
            </div>
        </#list>
        </div>

        <div class="details-container cf">

            <header>
                <div class="exception">
                    <h3 class="exc-title">
                    <#list Stacks.getStackTrace() as name>
                        <#if name_index == 0 >
                            <span class="exc-title-primary">${(name.getClassName())!""}</span>
                        <#else >
                        ${(name.getClassName())!""}
                        </#if>
                    </#list>
                    </h3>

                    <p class="exc-message">
                    ${(Stacks.getMessage())!""}
                    </p>
                </div>
            </header>

            <div class="frame-code-container">
            <#list Stacks.getStackTrace() as stack>
            ${(stack)!""}

                <div class="frame-code  <#if stack_index == 0 >active</#if>" id="frame-code-${stack_index!"0"}">
                    <div class="frame-file">
                        <#assign pathFile = (stack.getFileName() + " Method Action => " +
                        stack.getMethodName() + ":" + stack.getLineNumber())!"" >
                        <strong>${pathFile}</strong>
                    </div>
                </div>
            </#list>
            </div>
        </div>
    </div>
</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
    $(function () {
        prettyPrint();

        var $frameLines = $('[id^="frame-line-"]');
        var $activeLine = $('.frames-container .active');
        var $activeFrame = $('.active[id^="frame-code-"]').show();
        var $container = $('.details-container');
        var headerHeight = $('header').css('height');

        var highlightCurrentLine = function () {
            // Highlight the active and neighboring lines for this frame:
            var activeLineNumber = +($activeLine.find('.frame-line').text());
            var $lines = $activeFrame.find('.linenums li');
            var firstLine = +($lines.first().val());

            $($lines[activeLineNumber - firstLine - 1]).addClass('current');
            $($lines[activeLineNumber - firstLine]).addClass('current active');
            $($lines[activeLineNumber - firstLine + 1]).addClass('current');
        }

        // Highlight the active for the first frame:
        highlightCurrentLine();

        $frameLines.click(function () {
            var $this = $(this);
            var id = /frame\-line\-([\d]*)/.exec($this.attr('id'))[1];
            var $codeFrame = $('#frame-code-' + id);

            if ($codeFrame) {
                $activeLine.removeClass('active');
                $activeFrame.removeClass('active');

                $this.addClass('active');
                $codeFrame.addClass('active');

                $activeLine = $this;
                $activeFrame = $codeFrame;

                highlightCurrentLine();

                $container.animate({ scrollTop: headerHeight }, "fast");
            }
        });
    });
</script>
</body>
</html>