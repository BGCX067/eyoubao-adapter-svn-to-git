<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c-rt.tld"%>
<c:set var="pa" value="${paginationModel}"></c:set>
<c:set var="p" value="${paginationModel.pagination}"></c:set>
<script type="text/javascript">
    function gotoPage()
    {
        var page = document.getElementById("Page").value;
        var regex = /^[1-9][\d]*$/;
        
        // 输入的不是大于零的整数时，返回1
        if (!regex.test(page))
        {
            page = 1;
        }

        $("body").append('<form id="goto_form" action="" method="POST" style="display:none;"></form>');
        $("#goto_form").attr("action",'${pa.forwardPageUrl}' + page);
        $("#goto_form").submit();
    }
</script>
<table class="pagination">
        <tr>
            <td>
                <c:set var="count" value="${p.totalCount}"></c:set>
                页次： <font color="#ff0000">${p.pageNo}</font>/${p.totalPage}&nbsp;&nbsp;共有&nbsp;
                <font color="#ff0000">${count}</font>&nbsp;条信息&nbsp;&nbsp;&nbsp; 
                <c:choose>
                    <c:when test="${pa.firstEnable}">
                        <a href="${pa.forwardFirstURL}">第一页</a>&nbsp; 
                    </c:when>
                    <c:otherwise>
                        <span style="color:#aaa;">第一页</span>&nbsp; 
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pa.prevEnable}">
                        <a href="${pa.forwardPrevURL}">上一页</a>&nbsp; 
                    </c:when>
                    <c:otherwise>
                        <span style="color:#aaa;">上一页</span>&nbsp; 
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pa.nextEnable}">
                        <a href="${pa.forwardNextURL}">下一页</a>&nbsp; 
                    </c:when>
                    <c:otherwise>
                        <span style="color:#aaa;">下一页</span>&nbsp; 
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pa.lastEnable}">
                        <a href="${pa.forwardLastURL}">最后页</a>&nbsp; 
                    </c:when>
                    <c:otherwise>
                        <span style="color:#aaa;">最后页</span>&nbsp; 
                    </c:otherwise>
                </c:choose>
                                            转到 <input id="Page" style="width:20px;" height="15" value="${p.pageNo}" name="Page" />页
                <input onclick="gotoPage()" type="button" value="跳转" name="GoPage" class="button_50"/>
            </td>
        </tr>
</table>