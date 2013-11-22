<#--Error:<br/>-->
<#--<pre>-->
<#--${message}-->
<#--</pre>-->
<#--<script type="text/javascript">-->
<#--function show() {-->
<#--document.getElementById("stack_trace").removeAttribute("style");-->
<#--}-->
<#--</script>-->

<#--<br/>-->
<#--<a id="show_link" href="#" onclick='show();'>See Stack Trace</a>-->

<#--<div id="stack_trace" style="display:none">-->
<#--<pre>-->
<#--${stack_trace}-->
<#--</pre>-->
<#--</div>-->

<@wrap with="/layouts/system_error">
    <@content for="title">404 - Resource Not Found</@content>
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