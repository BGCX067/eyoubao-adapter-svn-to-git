<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c-rt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${base}/css/global.css" type="text/css" rel="Stylesheet" />
<script src="${thirdparty}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function()
    {
        var submit = function()
        {
            $.post($("#charge_form").attr('action'), $("#charge_form").serialize(), function(result)
            {
                window.close();
            });
        };

        $('#btn_success').click(function()
        {
            $('#success').attr('value', '1');
            submit();
        });

        $('#btn_failure').click(function()
        {
            $('#success').attr('value', '0');
            submit();
        });
    });
</script>
<title>模拟支付网关</title>
</head>
<body>
    <form id="charge_form" action="${base}/service/charge_callback.do">
        <input type="hidden" name="amount" value="${amount}">
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="success" id="success">
    </form>
    <table class="detail" style="width: 50%;">
        <thead>
            <tr>
                <td colspan="2">这是模拟的支付网关接口页面</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>充值金额</th>
                <td>${amount}</td>
            </tr>
            <tr>
                <td class="actions" colspan="2">
                    <input type="button" id="btn_success" value="充值成功">
                    <input type="button" id="btn_failure" value="充值失败">
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>