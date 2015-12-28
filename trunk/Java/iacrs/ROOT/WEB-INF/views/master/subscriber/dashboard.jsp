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
    <table class="detail">
        <thead>
            <tr>
                <td colspan="4">基本信息</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>姓名</th>
                <td>${user_model.archive.name}</td>
                <th>身份证号</th>
                <td>${user_model.archive.idcardno}</td>
            </tr>
            <tr>
                <th>联系电话</th>
                <td>${user_model.archive.phone}</td>
                <th>电子邮箱</th>
                <td>${user_model.archive.email}</td>
            </tr>
            <tr>
                <th>联系地址</th>
                <td colspan="3">${user_model.archive.address}</td>
            </tr>
        </tbody>
    </table>
    <table class="detail">
        <thead>
            <tr>
                <td colspan="4">账户信息</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>账户余额（元）</th>
                <td>${user_model.account.balance}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/subscriber/forward_charge.do">充值</a></td>
            </tr>
            <tr>
                <th>预授权（元）</th>
                <td>${user_model.account.preauth}</td>
            </tr>
        </tbody>
    </table>
    <div class="list-container">
        <h2>
            <label>最近充值记录</label>
        </h2>
        <table class="default">
            <thead>
                <tr>
                    <td>充值金额</td>
                    <td>充值日期</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${charge_records}">
                    <tr>
                        <td>${record.amount}</td>
                        <td>${record.chargeTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="list-container">
        <h2>
            <label>最近账单记录</label>
        </h2>
        <table class="default">
            <thead>
                <tr>
                    <td>租用车型</td>
                    <td>车牌号</td>
                    <td>开始日期</td>
                    <td>结束日期</td>
                    <td>租用时长（天）</td>
                    <td>租金（元/日）</td>
                    <td>保险费（元/日）</td>
                    <td>账单金额（元）</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="record" items="${rent_bills}">
                    <tr>
                        <td>${record.car.modelAdapter.model.name}</td>
                        <td>${record.car.car.licencePlate}</td>
                        <td>${record.bill.startTime}</td>
                        <td>${record.bill.endTime}</td>
                        <td>${record.bill.duration}</td>
                        <td>${record.bill.dailyRental}</td>
                        <td>${record.bill.dailyPremium}</td>
                        <td>${record.bill.amount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>