<@wrap with="/layouts/system_error">
    <@content for="title">404 - Resource Not Found</@content>
    <@content for="errormsg">
    <div class="container">
        <div class="stack-container">
            <div class="frames-container cf">
                <#list e.getStackTrace() as stack>
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
                            <#list e.getStackTrace() as name>
                                <#if name_index == 0 >
                                    <span class="exc-title-primary">${(name.getClassName())!""}</span>
                                <#else >
                                ${(name.getClassName())!""}
                                </#if>
                            </#list>
                        </h3>

                        <p class="exc-message">
                        ${message!""}
                        </p>
                    </div>
                </header>

                <div class="frame-code-container">
                    <#list e.getStackTrace() as stack>
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
    </@content>
    <@content for="errorHuman">
    <div class="container">
        <div class="stack-container">

            <p class="exc-message">

                Error message: <span>${message}</span> <br>
                Stack trace:
            </p>
            <a id="show_link" href="#" onclick='show();'>See Stack Trace</a>

            <div id="stack_trace" style="display:none">
<pre>
${stack_trace}
</pre>
            </div>
        </div>
        <script type="text/javascript">
            function show() {
                document.getElementById("stack_trace").removeAttribute("style");
            }
        </script>
    </div>
    </@content>
</@wrap>