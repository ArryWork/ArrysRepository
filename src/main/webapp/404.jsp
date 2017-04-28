<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>


<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="${basePath}/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath}/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/resources/css/animate.min.css" rel="stylesheet">
    <link href="${basePath}/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="middle-box text-center animated fadeInDown">
        <h1>404</h1>
        <h3 class="font-bold">页面未找到！</h3>

        <div class="error-desc">
            抱歉，页面好像去火星了~
            <c:if test="${sessionScope.user.fid !=null}">
                <a href="/user/goHome.do">回到主页</a>
            </c:if>
            <c:if test="${sessionScope.admin.fid !=null}">
                <a href="/admin/goHome.do">回到主页</a>
            </c:if>
            <c:if test="${sessionScope.user.fid==null} && ${sessionScope.admin.fid==null }">
                <a href="getPage.html">回到主页</a>
            </c:if>
        </div>
    </div>
    <script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
    <script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>
