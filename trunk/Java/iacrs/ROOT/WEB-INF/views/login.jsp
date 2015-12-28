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
        $("#username").focus();
        if (parent.window != window)
        {
            $("#login_form").attr("action", "${base}/forward_login.do");
            $("#login_form").attr("target", "_top");
            $("#login_form").submit();
        }

        $('#btn_register').click(function()
        {
            window.location = '${base}/forward_register.do';
        });

        $('#btn_mock').click(function()
        {
            window.open('${base}/mock/forward_mock.do');
        });
    });
</script>
<title>会员登录-${system_name}</title>
</head>
<body>
    <form id="login_form" action="${base}/login.do" method="post">
        <table class="detail" style="width: 30%;">
            <thead>
                <tr>
                    <td colspan="2">会员登录</td>
                </tr>
            </thead>
            <tbody>
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null && SPRING_SECURITY_LAST_EXCEPTION.message != null}">
                    <tr>
                        <td colspan="2">${SPRING_SECURITY_LAST_EXCEPTION.message}</td>
                    </tr>
                </c:if>
                <tr>
                    <th>用户名</th>
                    <td>
                        <input type="text" name="j_username" id="username" />
                    </td>
                </tr>
                <tr>
                    <th>密码</th>
                    <td>
                        <input type="password" name="j_password" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="actions">
                        <input type="submit" value="登录" />
                        <input type="button" id="btn_register" value="注册">
                        <input type="button" id="btn_mock" value="模拟刷卡">
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>