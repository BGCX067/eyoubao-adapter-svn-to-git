<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c-rt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<title>${system_name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${base}/css/global.css" type="text/css" rel="Stylesheet" />
<script src="${thirdparty}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function()
    {
        $(":radio").each(function()
        {
            $(this).bind("dblclick", function()
            {
                var data = $(this).val().split(",");
                var id = data[0];
                var name = data[1];
                var result = {
                    'id' : id,
                    'name' : name
                };
                window.returnValue = result;
                window.close();
            });
        });
    });
</script>
</head>
<body>
    <div class="list-container">
        <h2>
            <label>车型列表</label>
        </h2>
        <table class="default">
            <thead>
                <tr>
                    <td></td>
                    <td>车型</td>
                    <td>品牌</td>
                    <td>款式</td>
                    <td>车厢类型</td>
                    <td>档位类型</td>
                    <td>排量</td>
                    <td>日租金（元）</td>
                    <td>日保险费（元）</td>
                    <td>预授权（元）</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${paginationModel.pagination.records}">
                    <tr>
                        <td>
                            <input type="radio" name="id" value="${record.model.id},${record.model.name}" />
                        </td>
                        <td>${record.model.category}</td>
                        <td>${record.model.brand}</td>
                        <td>${record.model.style}</td>
                        <td>${record.model.coachType}</td>
                        <td>${record.model.gearType}</td>
                        <td>${record.model.sweptVolume}</td>
                        <td>${record.price.dailyRental}</td>
                        <td>${record.price.dailyPremium}</td>
                        <td>${record.price.preauth}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="10"><jsp:include page="../../../common/pagination.jsp" /></td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>