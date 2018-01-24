    <%--<a href="http://139.224.49.192:8080/jiacerconsole/index" target="_parent">to -index</a>--%>
        <%--<a target="_parent" href="../html/applys/1/test.html">to-parent-test-html</a>--%>
            <script>
        var url=window.location.href
        console.log(window.location.href)
        var params=url.split('?')[1]
        console.log(params);
            window.parent.location.href="../html/applys/1/formAudit.html?"+params;
            </script>
        <%--<a href="../html/applys/1/test.html">to-iframe-html</a>--%>
        <%--<webview id="mainFrame" name="mainFrame" src="http://139.224.49.192:8080/jiacerconsole/pages/form.html"  style="display:inline-flex; width:800px; height:800px" nodeintegration></webview>--%>