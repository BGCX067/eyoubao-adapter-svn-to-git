<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c-rt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${system_name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${base}/css/global.css" type="text/css" rel="Stylesheet" />
<script src="${thirdparty}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function()
    {

    });
</script>
</head>
<body>
    <div class="list-container">
        <h2>
            <label>车辆列表</label>
        </h2>
        <table class="default">
            <thead>
                <tr>
                    <td>车型图片</td>
                    <td>车型</td>
                    <td>日租金（元）</td>
                    <td>日保险费（元）</td>
                    <td>预授权（元）</td>
                    <td>车牌号</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${paginationModel.pagination.records}">
                    <tr>
                        <td><img src="${base}/${record.modelAdapter.model.imagePath}" width="150" height="80"/></td>
                        <td>${record.modelAdapter.model.name}</td>
                        <td>${record.car.dailyRental}</td>
                        <td>${record.car.dailyPremium}</td>
                        <td>${record.car.preauth}</td>
                        <td>${record.car.licencePlate}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7"><jsp:include page="../../common/pagination.jsp" /></td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>