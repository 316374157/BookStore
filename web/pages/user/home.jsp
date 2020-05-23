<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <jsp:include page="../../public/base.jsp"/>
    <script type="text/javascript">
        $(function () {
            $("#pn_input").keydown(function (e) {
                if(e.keyCode === 13)
                window.location.href = "BookServlet?method=getAllHomeAfter&pageNow="+ $("#pn_input").val();
            })
            $(".btn_add").click(function () {
                $.getJSON("CartServlet?method=add&id="+$(this).attr("addid"),function (data) {
                    $("#totalCount").text(data.total);
                    $("#title").text("您刚刚将【"+data.title+"】加入购物车");
                });

            })
        })
    </script>
</head>
<body>
<jsp:include page="../../public/user-header.jsp"/>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="BookServlet" method="get">
                <input type="hidden" name="method" value="getAllHomeByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
                <span>当前购物车中有<span id="totalCount">${empty cart.totalCount ?0:cart.totalCount}</span>件商品</span>
                <div>
                    <span id="title"></span>
                </div>
        </div>
        <c:forEach items="${page.pageData}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.title}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button addid="${book.id}" class="btn_add">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="page_nav">
        <a href="${url}1">首页</a>
        <c:if test="${page.hasPrevious}">
            <a href="${url}${page.pageNow-1}">上一页</a>
        </c:if>
        <c:choose>
            <c:when test="${page.totalPages <= 5}">
                <c:set var="begin" scope="page" value="1"/>
                <c:set var="end" scope="page" value="${page.totalPages}"/>
            </c:when>
            <c:when test="${page.totalPages > 5}">
                <c:choose>
                    <c:when test="${page.pageNow<=3}">
                        <c:set var="begin" scope="page" value="1"/>
                        <c:set var="end" scope="page" value="5"/>
                    </c:when>
                    <c:when test="${page.pageNow +2 >=page.totalPages}">
                        <c:set var="begin" scope="page" value="${page.totalPages-4}"/>
                        <c:set var="end" scope="page" value="${page.totalPages}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" scope="page" value="${page.pageNow-2}"/>
                        <c:set var="end" scope="page" value="${page.pageNow+2}"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        <c:forEach begin="${begin}" end="${end}" var="pnum">
            <c:if test="${pnum == page.pageNow}">
                <span style="color: blue">【${page.pageNow}】</span>
            </c:if>
            <c:if test="${pnum != page.pageNow}">
                <a href="${url}${pnum}">${pnum}</a>
            </c:if>
        </c:forEach>
        <c:if test="${page.hasNext}">
            <a href="${url}${page.pageNow+1}">下一页</a>
        </c:if>
        <a href="${url}${page.totalPages}">末页</a>
        共${page.totalPages}页，${page.totalCount}条记录 到第<input value="${page.pageNow}" name="pageNow" id="pn_input"/>页
    </div>

</div>

<jsp:include page="../../public/bottom.jsp"/>
</body>
</html>