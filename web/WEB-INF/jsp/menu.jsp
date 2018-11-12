<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>load_method</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0
        }

        html, body {
            height: 100%
        }

        #nav {
            float: left;
            width: 5%;
            height: 100%;
            background-color: antiquewhite
        }

        #con {
            float: right;
            width: 95%;
            height: 100%;
            background-color: aqua
        }
    </style>
    <script>
        $(document).ready(function () {
            //页面加载的时候，内容框默认显示 a.html
            $('#con').load('${pageContext.request.contextPath}/a.jsp');
            //单击 a 链接，加载 a.html
            $("#a1").click(function () {
                $('#con').load('${pageContext.request.contextPath}/a.jsp');
            });
            //单击 b 链接，加载 b.html
            $("#a2").click(function () {
                $('#con').load('${pageContext.request.contextPath}/b.jsp');
            });
        })
    </script>
</head>
<body>
<ul id="nav">
    <li><a href="#" id="a1">aaa</a></li>
    <li><a href="#" id="a2">bbb</a></li>
</ul>
<div id="con"></div>
</body>
</html>