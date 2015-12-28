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
        $('#btn_register').click(function()
        {
            window.location = '${base}/forward_register.do';
        });
    });
</script>
<title>${system_name}</title>
</head>
<body>
    <form action="${base}/master/modify_password.do" method="post">
        <table class="detail">
            <thead>
                <tr>
                    <td colspan="2">修改密码</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>旧密码</th>
                    <td>
                        <input type="password" name="oldPassword" id="oldPassword" />
                    </td>
                </tr>
                <tr>
                    <th>新密码</th>
                    <td>
                        <input type="password" name="newPassword" />
                    </td>
                </tr>
                <tr>
                    <th>确认密码</th>
                    <td>
                        <input type="password" name="c_newPassword" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="actions">
                        <input type="submit" value="确定" />
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>