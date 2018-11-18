<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="background-color" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div>
    <form method="post" action="${pageContext.request.contextPath}/ElefeeController/addRecordEle.action" enctype="multipart/form-data">
        <input type="file" name="file" width="120px">
        <input id="sub" type="submit" value="导入excel表格">
    </form>
</div>
<div class="pager">

    <form action="${pageContext.request.contextPath}/ElefeeController/conditionquery.action" method="post"
          style="text-align: center">
        检索条件：站址名称:
        <input type="text" name="sitename">&nbsp&nbsp
        年份:
        <input type="text" name="year">&nbsp&nbsp
        月份:
        <input type="text" name="month">&nbsp&nbsp
        <input type="submit" value="搜 索">
    </form>
    <table border="2px" align="center" width="1550px">
        <tbody>
        <tr>
            <td width="100px">
                站址编号
            </td>
            <td width="100px">
                站址名称
            </td>
            <td width="50px">
                单价-元
            </td>
            <td width="100px">
                上月余额
            </td>
            <td width="100px">
                本月预存
            </td>
            <td width="100px">
                电表起度
            </td>
            <td width="100px">
                电表止度
            </td>
            <td width="100px">
                用电量-度
            </td>
            <td width="50px">
                损耗-元
            </td>
            <td width="50px">
                税费-元
            </td>
            <td width="100px">
                农电附加-元
            </td>
            <td width="100px">
                基站分摊比例-联通
            </td>
            <td width="100px">
                基站分摊比例-移动
            </td>
            <td width="100px">
                基站分摊比例-电信
            </td>
            <td width="100px">
                总费用-元
            </td>
            <td width="100px">
                抄表期间
            </td>
            <td width="100px">
                折月
            </td>
            <td width="100px">
                导入日期
            </td>
        </tr>
        <c:forEach items="${list}" var="ele">
            <tr>
                <td>
                        ${ele.sitenum}
                </td>
                <td>
                        ${ele.sitename}
                </td>
                <td>
                        ${ele.unitprice}
                </td>
                <td>
                        ${ele.balance}
                </td>
                <td>
                        ${ele.predeposit}
                </td>
                <td>
                        ${ele.meterrise}
                </td>
                <td>
                        ${ele.meterstop}
                </td>
                <td>
                        ${ele.eleconsumption}
                </td>
                <td>
                        ${ele.loss}
                </td>
                <td>
                        ${ele.taxation}
                </td>
                <td>
                        ${ele.agriculturaleleadd}
                </td>
                <td>
                        ${ele.bSARUnicom}
                </td>
                <td>
                        ${ele.bSARMobile}
                </td>
                <td>
                        ${ele.bSARTelecom}
                </td>
                <td>
                        ${ele.totalcost}
                </td>
                <td>
                        ${ele.meterreading}
                </td>
                <td>
                        ${ele.monthfolding}
                </td>
                <td>
                    <fmt:formatDate value='${ele.importdate}' type='time' pattern='yyyy-MM-dd'/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/ElefeeController/down.action" method="post">
        <input type="hidden" value="${page.totalPageCount}" name="totalPageCount">
        <input type="hidden" value="${page.pageNow}" name="pageNow">
        <input name="button" value="导出为excel表格" type="submit">
    </form>
    <br>

    <div align="center">
        <font size="2">共 ${page.totalPageCount} 页</font>
        <font size="2">第 ${page.pageNow} 页</font>
        <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=1">首页</a>
        <c:choose>
            <c:when test="${page.pageNow - 1 > 0}">
                <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=${page.pageNow - 1}">上一页</a>
            </c:when>
            <c:when test="${page.pageNow - 1 <= 0}">
                <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=1">上一页</a> </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${page.totalPageCount==0}">
                <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=${page.pageNow}">下一页</a>
            </c:when>
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">
                <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=${page.pageNow + 1}">下一页</a>
            </c:when>
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
                <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=${page.totalPageCount}">下一页</a>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${page.totalPageCount==0}">
                <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=${page.pageNow}">尾页</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/ElefeeController/findElePage.action?pageNow=${page.totalPageCount}">尾页</a>
            </c:otherwise>
        </c:choose>
    </div>
    <%--
        <a target="_blank" href="${pageContext.request.contextPath}/.action?isPrint=true"
        class="button button-primary button-small">导出到Excel表格</a>
    --%>


</div>

</body>
</html>
